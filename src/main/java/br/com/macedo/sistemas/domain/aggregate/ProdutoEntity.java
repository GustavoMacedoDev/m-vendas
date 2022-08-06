package br.com.macedo.sistemas.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "produto", uniqueConstraints = {@UniqueConstraint(columnNames = "nome_produto")})
@Getter
@Setter
public class ProdutoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "valor_atual", nullable = false)
    private BigDecimal valorAtual;

    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL)
    private Set<CategoriaProdutoEntity> categoriasVinculadas = new HashSet<>();


}