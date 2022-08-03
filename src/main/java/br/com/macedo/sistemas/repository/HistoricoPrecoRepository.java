package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.HistoricoPrecoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class HistoricoPrecoRepository implements PanacheRepository<HistoricoPrecoEntity> {

    public List<HistoricoPrecoEntity> buscaHistoricoPrecoPeloIdProduto(Long idProduto) {
        return find("produto.idProduto", idProduto).list();
    }
}
