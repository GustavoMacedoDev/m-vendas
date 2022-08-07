package br.com.macedo.sistemas.repository;

import br.com.macedo.sistemas.domain.aggregate.PedidoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<PedidoEntity> {
}
