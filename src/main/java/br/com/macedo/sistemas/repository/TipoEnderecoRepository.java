package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.TipoEnderecoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class TipoEnderecoRepository implements PanacheRepository<TipoEnderecoEntity> {


    public Optional<TipoEnderecoEntity> findByNomeTipoEndereco(String nomeTipoEndereco) {
        return find("nomeTipoEndereco", nomeTipoEndereco).firstResultOptional();
    }
}
