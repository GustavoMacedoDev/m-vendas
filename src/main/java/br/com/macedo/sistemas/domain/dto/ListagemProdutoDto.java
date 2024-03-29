package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.enums.StatusProdutoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ListagemProdutoDto {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal valorAtual;
    private StatusProdutoEnum status;
}
