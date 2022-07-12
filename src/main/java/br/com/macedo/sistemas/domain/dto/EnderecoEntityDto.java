package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.TipoEnderecoEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoEntityDto implements Serializable {
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String complemento;
    private TipoEnderecoEntity tipoEndereco;
}
