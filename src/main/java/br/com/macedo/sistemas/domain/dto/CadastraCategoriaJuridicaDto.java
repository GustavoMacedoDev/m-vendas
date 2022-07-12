package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CadastraCategoriaJuridicaDto implements Serializable {
    private String nomeCategoriaJuridica;
    private String codigoCategoriaJuridica;
}
