package br.com.macedo.sistemas.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ListaProdutoHistoricoPrecoDto implements Serializable {
    private String nomeProduto;
    private BigDecimal valorAtual;
    private List<HistoricoPrecoEntityDto> historicoPreco;


}
