package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CategoriaProdutoRepository implements PanacheRepository<CategoriaProdutoEntity> {

    public List<CategoriaProdutoEntity> buscaProdutosPorCategoria(Long idCategoria) {
        return find("id.categoria.idCategoria", idCategoria).list();
    }
}
