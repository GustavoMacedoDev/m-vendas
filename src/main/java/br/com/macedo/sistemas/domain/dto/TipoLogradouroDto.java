package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class TipoLogradouroDto implements Serializable {
    private final Long idTipoLogradouro;
    private final String nomeTipoLogradouro;
}
