package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class LogradouroEntityDto implements Serializable {
    private final Long idLogradouro;
    private final String nomeLogradouro;
    private final TipoLogradouroDto tipoLogradouro;
}
