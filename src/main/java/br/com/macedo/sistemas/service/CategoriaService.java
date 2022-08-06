package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaEntity;
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
    CategoriaRepository categoriaProdutoRepository;


    public List<ListagemCategoriaProdutoDto> listaCategorias() {
        List<CategoriaEntity> listaCategorias = CategoriaEntity.listAll();
        List<ListagemCategoriaProdutoDto> listaCategoriasResponse = new ArrayList<>();

        for(CategoriaEntity categoriaProduto : listaCategorias) {
            ListagemCategoriaProdutoDto listagemCategoriaProdutoDto = new ListagemCategoriaProdutoDto();
            listagemCategoriaProdutoDto.setIdCategoriaProduto(categoriaProduto.getIdCategoria());
            listagemCategoriaProdutoDto.setNomeCategoriaProduto(categoriaProduto.getNomeCategoria());

            listaCategoriasResponse.add(listagemCategoriaProdutoDto);
        }

        return listaCategoriasResponse;
    }

    @Transactional
    public MensagemResposta cadastraCategoria(CadastraCategoriaProdutoDto cadastraCategoriaProdutoDto) {
        //verifica se categoria ja esta cadastrada
        validaCategoriaProdutoCadastrada(cadastraCategoriaProdutoDto.getNomeCategoriaProduto().toUpperCase());
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNomeCategoria(cadastraCategoriaProdutoDto.getNomeCategoriaProduto().toUpperCase());

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
                categoriaProdutoRepository.buscaCategoriaPeloNome(nomeCategoriaProduto);

        if(categoriaProdutoEntity.isPresent()) {
            throw new ErroCadastralException("Categoria já cadastrada");
        }

    }

    private CategoriaEntity buscaCategoriaPeloNome(String nomeCategoriaProduto) {
        Optional<CategoriaEntity> categoriaProdutoEntity =
                categoriaProdutoRepository.buscaCategoriaPeloNome(nomeCategoriaProduto);

        return categoriaProdutoEntity.orElseThrow(() -> new ErroCadastralException("Categoria Não Encontrada"));
    }


    public CategoriaEntity buscaCategoriaPeloId(Long idCategoria) {
        Optional<CategoriaEntity> categoriaProdutoEntity = categoriaProdutoRepository.findByIdOptional(idCategoria);

        return categoriaProdutoEntity.orElseThrow(() -> new ErroCadastralException("Categoria não encontrada"));
    }

    public DetalhaCategoriaProdutoDto listaCategoriaPeloId(Long idCategoria) {
        CategoriaEntity categoriaProduto = buscaCategoriaPeloId(idCategoria);

        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = new DetalhaCategoriaProdutoDto();
        detalhaCategoriaProdutoDto.setIdCategoriaProduto(categoriaProduto.getIdCategoria());
        detalhaCategoriaProdutoDto.setNomeCategoriaProduto(categoriaProduto.getNomeCategoria());

        return detalhaCategoriaProdutoDto;
    }

    public DetalhaCategoriaProdutoDto listaCategoriaPeloNome(String nomeCategoria) {
        CategoriaEntity categoriaProduto = buscaCategoriaPeloNome(nomeCategoria.toUpperCase());

        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = new DetalhaCategoriaProdutoDto();
        detalhaCategoriaProdutoDto.setIdCategoriaProduto(categoriaProduto.getIdCategoria());
        detalhaCategoriaProdutoDto.setNomeCategoriaProduto(categoriaProduto.getNomeCategoria());

        return detalhaCategoriaProdutoDto;
    }

    @Transactional
    public MensagemResposta alteraDadosCategoria(Long idCategoria,
                                                 AlteraDadosCategoriaProdutoDto alteraDadosCategoriaProdutoDto) {
        CategoriaEntity categoriaEntity = buscaCategoriaPeloId(idCategoria);
        categoriaEntity.setNomeCategoria(alteraDadosCategoriaProdutoDto.getNomeCategoriaProduto().toUpperCase());

        try {
            categoriaEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao executar transação");
        }

        return new MensagemResposta(categoriaEntity.getIdCategoria(), "" +
                "Categoria alterada com sucesso");
    }

}
