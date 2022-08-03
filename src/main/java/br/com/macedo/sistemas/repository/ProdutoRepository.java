package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoEntity> {

    public Optional<ProdutoEntity> buscaProdutoPeloNome(String nomeProduto) {
        return find("nomeProduto", nomeProduto).firstResultOptional();
    }
}
