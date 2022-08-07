package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.ItemPedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastraPedidoDto implements Serializable {
    private LocalDateTime dataPedido;
    private Set<ItemPedidoDto> itens;
}
