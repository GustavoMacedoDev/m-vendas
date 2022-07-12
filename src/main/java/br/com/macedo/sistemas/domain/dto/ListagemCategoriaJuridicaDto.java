package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListagemCategoriaJuridicaDto implements Serializable {
    private final String codigoNomeCategoriaJuridica;

    public ListagemCategoriaJuridicaDto(CategoriaJuridicaEntity categoriaJuridica) {
        this.codigoNomeCategoriaJuridica = categoriaJuridica.getCodigoCategoriaJuridica() + "-" + categoriaJuridica.getNomeCategoriaJuridica();
    }
}
