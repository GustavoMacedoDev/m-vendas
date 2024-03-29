package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import br.com.macedo.sistemas.domain.aggregate.HistoricoPrecoEntity;
import br.com.macedo.sistemas.domain.aggregate.ItemPedidoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.domain.dto.AlteraDadosProdutoDto;
import br.com.macedo.sistemas.domain.dto.CadastraProdutoDto;
import br.com.macedo.sistemas.domain.dto.CategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.ListaProdutoPorIdDto;
import br.com.macedo.sistemas.domain.dto.ListaProdutosPorCategoriaDto;
import br.com.macedo.sistemas.domain.dto.ListagemHistoricoPrecoDto;
import br.com.macedo.sistemas.domain.dto.ListagemProdutoDto;
import br.com.macedo.sistemas.domain.enums.StatusProdutoEnum;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class ProdutoService {

    private final static Logger LOGGER = Logger.getLogger(ProdutoService.class);

    @Inject
    HistoricoPrecoService historicoPrecoService;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    CategoriaService categoriaService;

    @Inject
    CategoriaProdutoService categoriaProdutoService;

    public List<ListagemProdutoDto> listaProdutos() {
        List<ProdutoEntity> listaProdutos = ProdutoEntity.listAll();

        List<ListagemProdutoDto> listaProdutosResponse = new ArrayList<>();
        for(ProdutoEntity produto : listaProdutos) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto();
            listagemProdutoDto.setIdProduto(produto.getIdProduto());
            listagemProdutoDto.setNomeProduto(produto.getNomeProduto());
            listagemProdutoDto.setValorAtual(produto.getValorAtual());
            listagemProdutoDto.setStatus(produto.getStatusProduto());

            listaProdutosResponse.add(listagemProdutoDto);
        }

        return listaProdutosResponse;
    }

    public List<CategoriaProdutoEntity> buscaProdutoPorIdCategoria(Long idCategoria) {

        return categoriaProdutoService.buscaProdutosPorIdCategoria(idCategoria);

    }

    @Transactional
    public MensagemResposta cadastrarProduto(CadastraProdutoDto cadastraProdutoDto) {
        validaExistenciaProduto(cadastraProdutoDto.getNomeProduto());


        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNomeProduto(cadastraProdutoDto.getNomeProduto().toUpperCase());
        produtoEntity.setValorAtual(cadastraProdutoDto.getValor());
        //produtoEntity.setStatusProduto(StatusProdutoEnum.ATIVO.getCodigo());


        List<CategoriaProdutoEntity> listaCategorias = new ArrayList<>();

        for(CategoriaProdutoDto categoriaProduto : cadastraProdutoDto.getCategorias()) {
            CategoriaProdutoEntity categoriaProdutoEntity = new CategoriaProdutoEntity();
            categoriaProdutoEntity.setCategoria(categoriaService.buscaCategoriaPeloId(categoriaProduto.getIdCategoria()));
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
        listaProdutoPorIdDto.setIdProduto(produtoEntity.getIdProduto());
        listaProdutoPorIdDto.setValorAtual(produtoEntity.getValorAtual());
        listaProdutoPorIdDto.setNomeProduto(produtoEntity.getNomeProduto());
        listaProdutoPorIdDto.setHistoricoPreco(historicoPrecoEntity);

        Set<DetalhaCategoriaProdutoDto> categorias = new HashSet<>();
        for (CategoriaProdutoEntity categoriaProdutoEntity: produtoEntity.getCategorias()){
            DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = new DetalhaCategoriaProdutoDto();
            detalhaCategoriaProdutoDto.setIdCategoria(categoriaProdutoEntity.getCategoria().getIdCategoria());
            detalhaCategoriaProdutoDto.setNomeCategoria(categoriaProdutoEntity.getCategoria().getNomeCategoria());

            categorias.add(detalhaCategoriaProdutoDto);
        }

        listaProdutoPorIdDto.setCategorias(categorias);

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

    @Transactional
    public MensagemResposta inativaProduto(Long idProduto) {
        ProdutoEntity produtoEntity = listaProdutoPorId(idProduto);
        produtoEntity.setStatusProduto(StatusProdutoEnum.INATIVO);

        try {
            produtoEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao inativar produto");
        }

        return new MensagemResposta(produtoEntity.getIdProduto(), "Produto inativado com sucesso");
    }

    public List<ListaProdutosPorCategoriaDto> listaProdutosPorCategoria(Long idCategoria) {
        List<CategoriaProdutoEntity> produtos = categoriaProdutoService.buscaProdutosPorIdCategoria(idCategoria);
        List<ListaProdutosPorCategoriaDto> listaProdutosResponse = new ArrayList<>();

        for(CategoriaProdutoEntity produto : produtos) {
            ListaProdutosPorCategoriaDto listaProdutosPorCategoriaDto = new ListaProdutosPorCategoriaDto();
            listaProdutosPorCategoriaDto.setNomeProduto(produto.getProduto().getNomeProduto());
            listaProdutosPorCategoriaDto.setValorAtual(produto.getProduto().getValorAtual());
            listaProdutosPorCategoriaDto.setStatusProduto(produto.getProduto().getStatusProduto());

            listaProdutosResponse.add(listaProdutosPorCategoriaDto);
        }

        return listaProdutosResponse;

    }

    public List<ListagemProdutoDto> listaProdutosPorStatus(String status) {
        List<ProdutoEntity> produtos = produtoRepository.listaProdutoPorStatus(status);

        List<ListagemProdutoDto> listaProdutosResponse = new ArrayList<>();
        for(ProdutoEntity produto : produtos) {
            ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto();
            listagemProdutoDto.setIdProduto(produto.getIdProduto());
            listagemProdutoDto.setNomeProduto(produto.getNomeProduto());
            listagemProdutoDto.setStatus(produto.getStatusProduto());

            listaProdutosResponse.add(listagemProdutoDto);
        }

        return listaProdutosResponse;

    }
}
