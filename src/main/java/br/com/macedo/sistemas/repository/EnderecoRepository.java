package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.EnderecoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<EnderecoEntity> {

    public List<EnderecoEntity> listaEnderecosPorIdTipo(Long codigoTipoEndereco) {
        return find("tipoEndereco.idTipoEndereco", codigoTipoEndereco).list();
    }
}
