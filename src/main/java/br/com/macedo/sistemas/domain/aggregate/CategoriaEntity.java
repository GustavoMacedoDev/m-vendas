package br.com.macedo.sistemas.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaEntity extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    private String nomeCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "id.categoria")
    private Set<CategoriaProdutoEntity> categorias = new HashSet<>();

    @JsonIgnore
    public List<ProdutoEntity> getProdutos() {
        List<ProdutoEntity> lista = new ArrayList<>();
        for(CategoriaProdutoEntity x : categorias) {
            lista.add(x.getProduto());
        }

        return lista;
    }


}
