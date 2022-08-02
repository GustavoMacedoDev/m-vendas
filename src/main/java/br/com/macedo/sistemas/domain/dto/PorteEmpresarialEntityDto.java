package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class PorteEmpresarialEntityDto implements Serializable {
    private final Long idPorteEmpresarial;
    private final String nomePorteEmpresarial;
    private final String descricaoPorteEmpresarial;
}
