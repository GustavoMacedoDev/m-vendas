package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrupoCnaeEntityDto implements Serializable {
    private final Long idGrupoCnae;
    private final String codigoDivisaoCnae;
    private final String descricaoDivisaoCnae;
    private final DivisaoCnaeEntityDto divisaoCnae;
}
