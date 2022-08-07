package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.ItemPedidoPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoEntityDto implements Serializable {

    private BigDecimal desconto;
    private int quantidade;
}
