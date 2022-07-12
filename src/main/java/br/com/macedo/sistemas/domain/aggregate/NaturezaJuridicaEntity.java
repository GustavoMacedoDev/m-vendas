package br.com.macedo.sistemas.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "natureza_juridica")
@Getter
@Setter
public class NaturezaJuridicaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_natureza_juridica", nullable = false)
    private Long idNaturezaJuridica;

    private String codigoNaturezaJuridica;

    private String nomeNaturezaJuridica;

    @ManyToOne
    @JoinColumn(name = "id_categoria_juridica")
    private CategoriaJuridicaEntity categoriaJuridica;

}
