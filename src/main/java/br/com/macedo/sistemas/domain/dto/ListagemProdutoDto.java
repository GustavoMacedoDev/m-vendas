package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ListagemProdutoDto {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal valorAtual;
}
