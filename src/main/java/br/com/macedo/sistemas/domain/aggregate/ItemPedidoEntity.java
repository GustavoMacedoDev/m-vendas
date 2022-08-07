package br.com.macedo.sistemas.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
public class ItemPedidoEntity extends PanacheEntityBase {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    @Column
    private BigDecimal desconto;

    @Column
    private int quantidade;

    @JsonIgnore
    public PedidoEntity getPedido() {
        return id.getPedido();
    }

    public void setPedido(PedidoEntity pedido) {
        id.setPedido(pedido);
    }

    public ProdutoEntity getProduto() {
        return id.getProduto();
    }

    public void setProduto(ProdutoEntity produto) {
        id.setProduto(produto);
    }






}
