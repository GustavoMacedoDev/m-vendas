package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaPedidosDto implements Serializable {
    private Long idPedido;
    private LocalDateTime dataPedido;
}
