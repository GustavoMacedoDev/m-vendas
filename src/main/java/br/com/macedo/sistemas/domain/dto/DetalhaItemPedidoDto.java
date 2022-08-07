package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhaItemPedidoDto {
    private BigDecimal desconto;
    private Integer quantidade;
    private ListagemProdutoDto produto;
}
