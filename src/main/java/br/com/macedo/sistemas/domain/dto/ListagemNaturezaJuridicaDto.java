package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.CategoriaJuridicaEntity;
import br.com.macedo.sistemas.domain.aggregate.NaturezaJuridicaEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListagemNaturezaJuridicaDto implements Serializable {
    private final String codigoNaturezaJuridica;
    private final String nomeNaturezaJuridica;
    private final String categoriaJuridica;

    public ListagemNaturezaJuridicaDto(NaturezaJuridicaEntity naturezaJuridicaEntity) {
        this.codigoNaturezaJuridica = naturezaJuridicaEntity.getCodigoNaturezaJuridica();
        this.nomeNaturezaJuridica = naturezaJuridicaEntity.getNomeNaturezaJuridica();
        this.categoriaJuridica = naturezaJuridicaEntity.getCategoriaJuridica().getCodigoCategoriaJuridica()
                + " - " + naturezaJuridicaEntity.getCategoriaJuridica().getNomeCategoriaJuridica();
    }
}
