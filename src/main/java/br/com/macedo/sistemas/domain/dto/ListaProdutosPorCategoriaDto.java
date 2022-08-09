package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.enums.StatusProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaProdutosPorCategoriaDto implements Serializable {
    private String nomeProduto;
    private BigDecimal valorAtual;
    private StatusProdutoEnum statusProduto;
}
