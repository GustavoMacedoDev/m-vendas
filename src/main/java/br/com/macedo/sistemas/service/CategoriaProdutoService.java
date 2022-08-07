package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.repository.CategoriaProdutoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CategoriaProdutoService {

    @Inject
    CategoriaProdutoRepository categoriaProdutoRepository;


    public List<CategoriaProdutoEntity> buscaProdutosPorIdCategoria(Long idCategoria) {

        return categoriaProdutoRepository.buscaProdutosPorCategoria(idCategoria);
    }
}
