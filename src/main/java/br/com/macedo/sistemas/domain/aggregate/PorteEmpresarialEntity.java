package br.com.macedo.sistemas.domain.aggregate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "porte_empresarial")
@Getter
@Setter
public class PorteEmpresarialEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_porte_empresarial", nullable = false)
    private Long idPorteEmpresarial;

    private String nomePorteEmpresarial;

    private String descricaoPorteEmpresarial;


}
