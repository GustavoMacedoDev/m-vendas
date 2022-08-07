package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhaPedidoDto implements Serializable {
    private Long idPedido;
    private LocalDateTime dataPedido;
    private Set<DetalhaItemPedidoDto> itens = new HashSet<>();
}
