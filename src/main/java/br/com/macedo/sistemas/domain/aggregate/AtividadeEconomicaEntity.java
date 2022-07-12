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
import java.util.List;

@Entity
@Table(name = "atividade_economica")
@Getter
@Setter
public class AtividadeEconomicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade_economica", nullable = false)
    private Long idAtividadeEconomica;

    private String idCnae;

    private String descricaoCnae;

    @ManyToOne
    @JoinColumn(name = "id_grupo_cnae")
    private GrupoCnaeEntity grupoCnae;

    @ManyToOne
    @JoinColumn(name = "id_observacao")
    private ObservacaoEntity observacaoEntity;

}
