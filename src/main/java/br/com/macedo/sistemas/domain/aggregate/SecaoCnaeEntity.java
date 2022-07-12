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
@Table(name = "secao_cnae")
@Getter
@Setter
public class SecaoCnaeEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secao_cnae", nullable = false)
    private Long idSecaoCnae;

    private String codigoCnae;

    private String descricaoCnae;

}
