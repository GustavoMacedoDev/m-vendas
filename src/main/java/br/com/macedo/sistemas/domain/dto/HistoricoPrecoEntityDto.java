package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class HistoricoPrecoEntityDto implements Serializable {
    private BigDecimal valor;
    private LocalDateTime dataAlteracao;
}
