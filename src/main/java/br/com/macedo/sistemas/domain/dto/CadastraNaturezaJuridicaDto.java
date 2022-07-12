package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class CadastraNaturezaJuridicaDto implements Serializable {
    private String codigoNaturezaJuridica;
    private String nomeNaturezaJuridica;
    private CategoriaJuridicaDto categoriaJuridicaDto;
}
