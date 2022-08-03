package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ListagemProdutoDto implements Serializable {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal valorAtual;
}
