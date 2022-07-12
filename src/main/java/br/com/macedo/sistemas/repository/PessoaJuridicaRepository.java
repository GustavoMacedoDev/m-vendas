package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.PessoaJuridicaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class PessoaJuridicaRepository implements PanacheRepository<PessoaJuridicaEntity> {

    public Optional<PessoaJuridicaEntity> findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResultOptional();
    }
}
