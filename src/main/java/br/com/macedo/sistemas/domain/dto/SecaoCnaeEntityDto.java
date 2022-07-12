package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecaoCnaeEntityDto implements Serializable {
    private final Long idSecaoCnae;
    private final String codigoCnae;
    private final String descricaoCnae;
}
