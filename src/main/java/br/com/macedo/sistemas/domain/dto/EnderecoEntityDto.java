package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class EnderecoEntityDto implements Serializable {
    private final LogradouroEntityDto logradouro;
    private final String numero;
    private final String cep;
    private final String complemento;
    private final BairroEntityDto bairro;
}
