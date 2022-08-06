package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.CategoriaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<CategoriaEntity> {

    public Optional<CategoriaEntity> buscaCategoriaPeloNome(String nomeCategoria){
        return find("nomeCategoria", nomeCategoria).firstResultOptional();
    }


}
