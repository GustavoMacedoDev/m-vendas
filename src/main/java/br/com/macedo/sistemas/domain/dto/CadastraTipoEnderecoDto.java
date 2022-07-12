package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CadastraTipoEnderecoDto implements Serializable {
    @NotNull(message = "O campo nomeTipoEndereco está vazio")
    @Size(message = "O campo nomeTipoEndereco deve estar entre {min} e {max} caracteres", min = 3, max = 30)
    private String nomeTipoEndereco;
}
