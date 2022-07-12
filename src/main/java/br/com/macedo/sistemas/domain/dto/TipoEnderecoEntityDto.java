package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TipoEnderecoEntityDto implements Serializable {
    private final Long idTipoEndereco;
    private final String nomeTipoEndereco;
}
