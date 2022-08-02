package br.com.macedo.sistemas.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_logradouro")
@Getter
@Setter
public class TipoLogradouroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_logradouro", nullable = false)
    private Long idTipoLogradouro;

    @Column(name = "nome_tipo_logradouro", nullable = false)
    private String nomeTipoLogradouro;



}
