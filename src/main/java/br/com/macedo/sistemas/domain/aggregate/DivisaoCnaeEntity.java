package br.com.macedo.sistemas.domain.aggregate;

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
@Table(name = "divisao_cnae")
@Getter
@Setter
public class DivisaoCnaeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_divisao_cnae", nullable = false)
    private Long idDivisaoCnae;

    private  String codigoDivisaoCnae;

    private String descricaoDivisaoCnae;

    @ManyToOne
    @JoinColumn(name = "id_secao_cnae")
    private SecaoCnaeEntity secaoCnae;

}
