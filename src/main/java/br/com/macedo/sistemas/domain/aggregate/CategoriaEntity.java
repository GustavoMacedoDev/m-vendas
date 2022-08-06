package br.com.macedo.sistemas.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class CategoriaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    private String nomeCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "id.categoria", cascade = CascadeType.ALL)
    private List<CategoriaProdutoEntity> categoriasVinculadas;

    public List<ProdutoEntity> getProdutos() {
        List<ProdutoEntity> lista = new ArrayList<>();
        for(CategoriaProdutoEntity x : categoriasVinculadas) {
            lista.add(x.getProduto());
        }

        return lista;
    }


}
