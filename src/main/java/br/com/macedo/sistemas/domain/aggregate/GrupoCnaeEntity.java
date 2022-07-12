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
@Table(name = "grupo_cnae")
@Getter
@Setter
public class GrupoCnaeEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_cnae", nullable = false)
    private Long idGrupoCnae;

    private String codigoDivisaoCnae;

    private String descricaoDivisaoCnae;

    @ManyToOne
    @JoinColumn(name = "id_divisao_cnae")
    private DivisaoCnaeEntity divisaoCnae;
}
