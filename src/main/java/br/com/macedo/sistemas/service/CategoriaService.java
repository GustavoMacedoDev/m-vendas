package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaEntity;
import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.domain.dto.AlteraDadosCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.CadastraCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.ListagemCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.CategoriaRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoriaService {

    private final static Logger LOGGER = Logger.getLogger(CategoriaEntity.class);

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    ProdutoService produtoService;


    public List<ListagemCategoriaProdutoDto> listaCategorias() {
        List<CategoriaEntity> listaCategorias = CategoriaEntity.listAll();
        List<ListagemCategoriaProdutoDto> listaCategoriasResponse = new ArrayList<>();

        for(CategoriaEntity categoriaProduto : listaCategorias) {
            ListagemCategoriaProdutoDto listagemCategoriaProdutoDto = new ListagemCategoriaProdutoDto();
            listagemCategoriaProdutoDto.setIdCategoria(categoriaProduto.getIdCategoria());
            listagemCategoriaProdutoDto.setNomeCategoria(categoriaProduto.getNomeCategoria());

            listaCategoriasResponse.add(listagemCategoriaProdutoDto);
        }

        return listaCategoriasResponse;
    }

    @Transactional
    public MensagemResposta cadastraCategoria(CadastraCategoriaProdutoDto cadastraCategoriaProdutoDto) {
        //verifica se categoria ja esta cadastrada
        validaCategoriaProdutoCadastrada(cadastraCategoriaProdutoDto.getNomeCategoria().toUpperCase());
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNomeCategoria(cadastraCategoriaProdutoDto.getNomeCategoria().toUpperCase());

        try {
            categoria.persist();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao executar transação");
        }

        return new MensagemResposta(categoria.getIdCategoria(),
                "Categoria cadastrada com sucesso");
    }

    private void validaCategoriaProdutoCadastrada(String nomeCategoriaProduto) {
        Optional<CategoriaEntity> categoriaProdutoEntity =
                categoriaRepository.buscaCategoriaPeloNome(nomeCategoriaProduto.replace(" ","" ));

        if(categoriaProdutoEntity.isPresent()) {
            throw new ErroCadastralException("Categoria já cadastrada");
        }

    }

    private CategoriaEntity buscaCategoriaPeloNome(String nomeCategoriaProduto) {
        Optional<CategoriaEntity> categoriaProdutoEntity =
                categoriaRepository.buscaCategoriaPeloNome(nomeCategoriaProduto);

        return categoriaProdutoEntity.orElseThrow(() -> new ErroCadastralException("Categoria Não Encontrada"));
    }


    public CategoriaEntity buscaCategoriaPeloId(Long idCategoria) {
        Optional<CategoriaEntity> categoriaProdutoEntity = categoriaRepository.findByIdOptional(idCategoria);

        return categoriaProdutoEntity.orElseThrow(() -> new ErroCadastralException("Categoria não encontrada"));
    }

    public DetalhaCategoriaProdutoDto listaCategoriaPeloId(Long idCategoria) {
        CategoriaEntity categoriaProduto = buscaCategoriaPeloId(idCategoria);

        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = new DetalhaCategoriaProdutoDto();
        detalhaCategoriaProdutoDto.setIdCategoria(categoriaProduto.getIdCategoria());
        detalhaCategoriaProdutoDto.setNomeCategoria(categoriaProduto.getNomeCategoria());

        return detalhaCategoriaProdutoDto;
    }

    public DetalhaCategoriaProdutoDto listaCategoriaPeloNome(String nomeCategoria) {
        CategoriaEntity categoriaProduto = buscaCategoriaPeloNome(nomeCategoria.toUpperCase());

        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = new DetalhaCategoriaProdutoDto();
        detalhaCategoriaProdutoDto.setIdCategoria(categoriaProduto.getIdCategoria());
        detalhaCategoriaProdutoDto.setNomeCategoria(categoriaProduto.getNomeCategoria());

        return detalhaCategoriaProdutoDto;
    }

    @Transactional
    public MensagemResposta alteraDadosCategoria(Long idCategoria,
                                                 AlteraDadosCategoriaProdutoDto alteraDadosCategoriaProdutoDto) {
        validaCategoriaProdutoCadastrada(alteraDadosCategoriaProdutoDto.getNomeCategoria().toUpperCase());
        CategoriaEntity categoriaEntity = buscaCategoriaPeloId(idCategoria);
        categoriaEntity.setNomeCategoria(alteraDadosCategoriaProdutoDto.getNomeCategoria().toUpperCase());

        try {
            categoriaEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao executar transação");
        }

        return new MensagemResposta(categoriaEntity.getIdCategoria(), "" +
                "Categoria alterada com sucesso");
    }

    @Transactional
    public MensagemResposta deletaCategoria(Long idCategoria) {
        verificaVinculoComProdutos(idCategoria);

        try {
            categoriaRepository.deleteById(idCategoria);
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao tentar excluir categoria");
        }

        return new MensagemResposta(1L,"Categoria excluida com sucesso");
    }

    private void verificaVinculoComProdutos(Long idCategoria) {
        List<CategoriaProdutoEntity> produtos = produtoService.buscaProdutoPorIdCategoria(idCategoria);

        System.out.println(produtos);
        if (!produtos.isEmpty()) {
            throw new ErroCadastralException("Categoria esta vinculada a produtos");
        }
    }
}
