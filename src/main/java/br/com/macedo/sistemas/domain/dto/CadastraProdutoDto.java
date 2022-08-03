package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CadastraProdutoDto implements Serializable {
    private String nomeProduto;
    private BigDecimal valor;
}
