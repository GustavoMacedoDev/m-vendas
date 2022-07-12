package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.NaturezaJuridicaEntity;
import br.com.macedo.sistemas.domain.dto.CadastraNaturezaJuridicaDto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class NaturezaJuridicaRepository implements PanacheRepository<NaturezaJuridicaEntity> {

    public Optional<NaturezaJuridicaEntity> buscaNaturezaPeloNome(CadastraNaturezaJuridicaDto cadastraNaturezaJuridicaDto) {
        return find("nomeNaturezaJuridica", cadastraNaturezaJuridicaDto.getNomeNaturezaJuridica()).firstResultOptional();
    }

}
