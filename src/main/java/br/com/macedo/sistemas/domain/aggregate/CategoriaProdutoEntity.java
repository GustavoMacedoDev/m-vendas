package br.com.macedo.sistemas.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "categoria_produto")
@NoArgsConstructor
@Getter
@Setter
public class CategoriaProdutoEntity extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private CategoriaProdutoPk id = new CategoriaProdutoPk();


    public CategoriaProdutoEntity(ProdutoEntity produto, CategoriaEntity categoria) {
        super();
        id.setCategoria(categoria);
        id.setProduto(produto);
    }

    @JsonIgnore
    public ProdutoEntity getProduto() {
        return id.getProduto();
    }

    public void setProduto(ProdutoEntity produto) {
        id.setProduto(produto);
    }

    public CategoriaEntity getCategoria() {
        return id.getCategoria();
    }

    public void setCategoria(CategoriaEntity categoria) {
        id.setCategoria(categoria);
    }



}
