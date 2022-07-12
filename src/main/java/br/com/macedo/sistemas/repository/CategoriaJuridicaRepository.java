package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoriaJuridicaRepository implements PanacheRepository<CategoriaJuridicaEntity> {

    public Optional<CategoriaJuridicaEntity> buscaCategoriaPeloNome(String nomeCategoriaJuridica) {
        return find("nomeCategoriaJuridica", nomeCategoriaJuridica).firstResultOptional();
    }
}
