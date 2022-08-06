package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import br.com.macedo.sistemas.domain.aggregate.HistoricoPrecoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.domain.dto.AlteraDadosProdutoDto;
import br.com.macedo.sistemas.domain.dto.CadastraProdutoDto;
import br.com.macedo.sistemas.domain.dto.CategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.ListaProdutoPorIdDto;
import br.com.macedo.sistemas.domain.dto.ListagemHistoricoPrecoDto;
import br.com.macedo.sistemas.domain.dto.ListagemProdutoDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.ProdutoRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoService {

    private final static Logger LOGGER = Logger.getLogger(ProdutoService.class);

    @Inject
    HistoricoPrecoService historicoPrecoService;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    CategoriaService categoriaService;

    public List<ListagemProdutoDto> listaProdutos() {
        List<ProdutoEntity> listaProdutos = ProdutoEntity.listAll();
        List<ListagemProdutoDto> listaProdutosResponse = new ArrayList<>();
        for(ProdutoEntity produto : listaProdutos) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto();
            listagemProdutoDto.setIdProduto(produto.getIdProduto());
            listagemProdutoDto.setNomeProduto(produto.getNomeProduto());
            listagemProdutoDto.setValorAtual(produto.getValorAtual());
            listagemProdutoDto.setCategorias(produto.getCategoriasVinculadas());

            listaProdutosResponse.add(listagemProdutoDto);
        }

        return listaProdutosResponse;
    }

    @Transactional
    public MensagemResposta cadastrarProduto(CadastraProdutoDto cadastraProdutoDto) {
        validaExistenciaProduto(cadastraProdutoDto.getNomeProduto());


        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNomeProduto(cadastraProdutoDto.getNomeProduto());
        produtoEntity.setValorAtual(cadastraProdutoDto.getValor());


        List<CategoriaProdutoEntity> listaCategorias = new ArrayList<>();

        for(CategoriaProdutoDto categoriaProduto : cadastraProdutoDto.getCategorias()) {
            CategoriaProdutoEntity categoriaProdutoEntity = new CategoriaProdutoEntity();
            categoriaProdutoEntity.setCategoria(categoriaService.buscaCategoriaPeloId(categoriaProduto.getIdCategoriaProduto()));
            categoriaProdutoEntity.setProduto(produtoEntity);

            listaCategorias.add(categoriaProdutoEntity);
        }


        try {
            produtoEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar produto");
        }

        try {
            PanacheEntityBase.persist(listaCategorias);
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar vinculo de categoria");
        }

        historicoPrecoService.cadastraHistoricoPreco(cadastraProdutoDto.getValor(), produtoEntity);

        return new MensagemResposta(produtoEntity.getIdProduto(),
                "Produto cadastrado com sucesso!Id: " + produtoEntity.getIdProduto());
    }

    private void validaExistenciaProduto(String nomeProduto) {
        Optional<ProdutoEntity> produto = produtoRepository.buscaProdutoPeloNome(nomeProduto);

        if (produto.isPresent()) {
            throw new ErroCadastralException("Produto já cadastrado");
        }
    }

    @Transactional
    public MensagemResposta alteraDadosProduto(Long idProduto, AlteraDadosProdutoDto alteraDadosProdutoDto) {
        Optional<ProdutoEntity> produto = produtoRepository.findByIdOptional(idProduto);
        if(produto.isEmpty()) {
            throw new ErroCadastralException("Produto não encontrado");
        }

        ProdutoEntity produtoEntity = ProdutoEntity.findById(idProduto);
        produtoEntity.setNomeProduto(alteraDadosProdutoDto.getNomeProduto());
        produtoEntity.setValorAtual(alteraDadosProdutoDto.getValor());

        try {
            produtoEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao alterar dados do produto");
        }

        historicoPrecoService.cadastraHistoricoPreco(alteraDadosProdutoDto.getValor(), produtoEntity);

        return new MensagemResposta(produtoEntity.getIdProduto(), "" +
                "Produto alterado com sucesso!");

    }

    public ProdutoEntity listaProdutoPorId(Long idProduto) {
        Optional<ProdutoEntity>produtoEntity = produtoRepository.findByIdOptional(idProduto);

        return produtoEntity.orElseThrow(() -> new ErroCadastralException("Produto não encontrado"));
    }


    public ListaProdutoPorIdDto buscaProdutoPorId(Long idProduto) {
        ProdutoEntity produtoEntity = listaProdutoPorId(idProduto);

        List<ListagemHistoricoPrecoDto> historicoPrecoEntity = buscaHistoricoAlteracaoPreco(idProduto);

        ListaProdutoPorIdDto listaProdutoPorIdDto = new ListaProdutoPorIdDto();
        listaProdutoPorIdDto.setValorAtual(produtoEntity.getValorAtual());
        listaProdutoPorIdDto.setNomeProduto(produtoEntity.getNomeProduto());
        listaProdutoPorIdDto.setHistoricoPreco(historicoPrecoEntity);

        return listaProdutoPorIdDto;

    }

    private List<ListagemHistoricoPrecoDto> buscaHistoricoAlteracaoPreco(Long idProduto) {
        List<HistoricoPrecoEntity> historicoPreco = historicoPrecoService.buscaHistoricoPorIdProduto(idProduto);
        List<ListagemHistoricoPrecoDto> listagemHistoricoPrecoResponse = new ArrayList<>();

        for(HistoricoPrecoEntity historicoPrecoEntity : historicoPreco) {
            ListagemHistoricoPrecoDto listagemHistoricoPrecoDto = new ListagemHistoricoPrecoDto();
            listagemHistoricoPrecoDto.setDataAlteracao(historicoPrecoEntity.getDataAlteracao());
            listagemHistoricoPrecoDto.setValor(historicoPrecoEntity.getValor());

            listagemHistoricoPrecoResponse.add(listagemHistoricoPrecoDto);

        }

        return listagemHistoricoPrecoResponse;

    }
}
