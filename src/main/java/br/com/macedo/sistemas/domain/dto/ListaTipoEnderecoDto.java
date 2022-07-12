package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.TipoEnderecoEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListaTipoEnderecoDto implements Serializable {
    private final String nomeTipoEndereco;

    public ListaTipoEnderecoDto(TipoEnderecoEntity tipoEndereco){
        this.nomeTipoEndereco = tipoEndereco.getNomeTipoEndereco();
    }
}
