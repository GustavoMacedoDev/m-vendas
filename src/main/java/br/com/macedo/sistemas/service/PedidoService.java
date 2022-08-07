package br.com.macedo.sistemas.service;

import br.com.macedo.sistemas.domain.aggregate.ItemPedidoEntity;
import br.com.macedo.sistemas.domain.aggregate.PedidoEntity;
import br.com.macedo.sistemas.domain.aggregate.ProdutoEntity;
import br.com.macedo.sistemas.domain.dto.CadastraPedidoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaItemPedidoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaPedidoDto;
import br.com.macedo.sistemas.domain.dto.ItemPedidoDto;
import br.com.macedo.sistemas.domain.dto.ListaPedidosDto;
import br.com.macedo.sistemas.domain.dto.ListagemProdutoDto;
import br.com.macedo.sistemas.domain.dto.ProdutoDto;
import br.com.macedo.sistemas.domain.utils.exceptions.ErroCadastralException;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.repository.PedidoRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class PedidoService {

    private final static Logger LOGGER = Logger.getLogger(ProdutoService.class);

    @Inject
    ProdutoService produtoService;

    @Inject
    PedidoRepository pedidoRepository;

    @Transactional
    public MensagemResposta cadastraPedido(CadastraPedidoDto cadastraPedidoDto) {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setDataPedido(LocalDateTime.now());

        try {
            pedido.persistAndFlush();
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar pedido");
        }

        List<ItemPedidoEntity> itens = new ArrayList<>();

        for(ItemPedidoDto itemPedido : cadastraPedidoDto.getItens()) {
            ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();
            itemPedidoEntity.setQuantidade(itemPedido.getQuantidade());
            itemPedidoEntity.setDesconto(itemPedido.getDesconto());
            itemPedidoEntity.setProduto(produtoService.listaProdutoPorId(itemPedido.getProduto().getIdProduto()));
            itemPedidoEntity.setPedido(pedido);

            itens.add(itemPedidoEntity);
        }

        try {
            PanacheEntityBase.persist(itens);
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new ErroCadastralException("Erro ao cadastrar itens do pedido");
        }

        return new MensagemResposta(pedido.getIdPedido(), "Pedido cadastrado com sucesso");

    }

    public List<ListaPedidosDto> listaPedidos() {
        List<PedidoEntity> listaPedidosEntity = PedidoEntity.listAll();
        List<ListaPedidosDto> listaPedidosResponse = new ArrayList<>();
        for (PedidoEntity pedidoEntity : listaPedidosEntity) {
            ListaPedidosDto  listaPedidosDto = new ListaPedidosDto();
            listaPedidosDto.setIdPedido(pedidoEntity.getIdPedido());
            listaPedidosDto.setDataPedido(pedidoEntity.getDataPedido());

            listaPedidosResponse.add(listaPedidosDto);
        }

        return listaPedidosResponse;
    }



    public DetalhaPedidoDto detalhaPedido(Long idPedido) {
        PedidoEntity pedidoEntity = buscaPedidoPeloId(idPedido);

        DetalhaPedidoDto detalhaPedidoDto = new DetalhaPedidoDto();
        detalhaPedidoDto.setIdPedido(pedidoEntity.getIdPedido());
        detalhaPedidoDto.setDataPedido(pedidoEntity.getDataPedido());

        Set<DetalhaItemPedidoDto> itens = new HashSet<>();
        for(ItemPedidoEntity itemPedido : pedidoEntity.getItens()) {
            DetalhaItemPedidoDto detalhaItemPedidoDto = new DetalhaItemPedidoDto();
            detalhaItemPedidoDto.setDesconto(itemPedido.getDesconto());
            detalhaItemPedidoDto.setQuantidade(itemPedido.getQuantidade());
            detalhaItemPedidoDto.setProduto(buscaProdutoPeloId(itemPedido.getProduto().getIdProduto()));

            itens.add(detalhaItemPedidoDto);
        }

        detalhaPedidoDto.setItens(itens);

        return detalhaPedidoDto;
    }

    private ListagemProdutoDto buscaProdutoPeloId(Long idProduto) {
        ProdutoEntity produtoEntity = produtoService.listaProdutoPorId(idProduto);

        ListagemProdutoDto listagemProdutoDto = new ListagemProdutoDto();
        listagemProdutoDto.setIdProduto(produtoEntity.getIdProduto());
        listagemProdutoDto.setNomeProduto(produtoEntity.getNomeProduto());
        listagemProdutoDto.setValorAtual(produtoEntity.getValorAtual());

        return listagemProdutoDto;
    }


    private PedidoEntity buscaPedidoPeloId(Long idPedido) {
        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findByIdOptional(idPedido);

        return pedidoEntity.orElseThrow(() -> new ErroCadastralException("Pedido não encontrado"));
    }
}
