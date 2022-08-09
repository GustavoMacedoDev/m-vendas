package br.com.macedo.sistemas.domain.aggregate;

import br.com.macedo.sistemas.domain.enums.StatusProdutoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "produto", uniqueConstraints = {@UniqueConstraint(columnNames = "nome_produto")})
@Getter
@Setter
public class ProdutoEntity extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "valor_atual", nullable = false)
    private BigDecimal valorAtual;

    @Enumerated(EnumType.ORDINAL)
    private StatusProdutoEnum statusProduto;

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<CategoriaProdutoEntity> categorias = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

    public ProdutoEntity() {}

    @JsonIgnore
    public List<PedidoEntity> getPedidos() {
        List<PedidoEntity> lista = new ArrayList<>();
        for (ItemPedidoEntity x : itens) {
            lista.add(x.getPedido());
        }

        return lista;
    }

}