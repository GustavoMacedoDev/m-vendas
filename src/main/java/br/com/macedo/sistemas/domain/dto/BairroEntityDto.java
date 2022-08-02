package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class BairroEntityDto implements Serializable {
    private final Long idBairro;
    private final String nomeBairro;
}
