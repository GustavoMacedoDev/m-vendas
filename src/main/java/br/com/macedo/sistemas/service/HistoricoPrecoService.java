package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.HistoricoPrecoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.repository.HistoricoPrecoRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class HistoricoPrecoService {

    private final static Logger LOGGER = Logger.getLogger(HistoricoPrecoService.class);

    @Inject
    HistoricoPrecoRepository historicoPrecoRepository;

    @Transactional
    public HistoricoPrecoEntity cadastraHistoricoPreco(BigDecimal valor, ProdutoEntity produtoEntity) {
        HistoricoPrecoEntity historicoPrecoEntity = new HistoricoPrecoEntity();
        historicoPrecoEntity.setDataAlteracao(LocalDateTime.now());
        historicoPrecoEntity.setValor(valor);
        historicoPrecoEntity.setProduto(produtoEntity);

        try {
            historicoPrecoEntity.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar historico de preco");
        }

        return historicoPrecoEntity;
    }

    public List<HistoricoPrecoEntity> buscaHistoricoPorIdProduto(Long idProduto) {
        return historicoPrecoRepository.buscaHistoricoPrecoPeloIdProduto(idProduto);
    }
}
