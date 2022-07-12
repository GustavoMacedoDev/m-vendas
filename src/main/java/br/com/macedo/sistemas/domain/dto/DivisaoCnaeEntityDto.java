package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DivisaoCnaeEntityDto implements Serializable {
    private final Long idDivisaoCnae;
    private final String codigoDivisaoCnae;
    private final String descricaoDivisaoCnae;
    private final SecaoCnaeEntityDto secaoCnae;
}
