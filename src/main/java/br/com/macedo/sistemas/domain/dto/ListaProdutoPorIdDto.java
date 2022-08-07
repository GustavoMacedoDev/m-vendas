package br.com.macedo.sistemas.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ListaProdutoPorIdDto implements Serializable {

    private Long idProduto;
    private String nomeProduto;
    private BigDecimal valorAtual;
    private List<ListagemHistoricoPrecoDto> historicoPreco;
    private Set<DetalhaCategoriaProdutoDto> categorias;
}
