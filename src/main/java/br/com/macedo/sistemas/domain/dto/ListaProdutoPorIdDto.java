package br.com.macedo.sistemas.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ListaProdutoPorIdDto implements Serializable {
    private String nomeProduto;
    private BigDecimal valorAtual;
    private List<ListagemHistoricoPrecoDto> historicoPreco;
}
