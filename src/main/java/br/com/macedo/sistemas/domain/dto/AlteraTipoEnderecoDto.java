package br.com.macedo.sistemas.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AlteraTipoEnderecoDto implements Serializable {
    private String nomeTipoEndereco;
}
