package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.BandeiraCartaoEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListagemBandeiraCartaoDto implements Serializable {
    private final Long idBandeiraCartao;
    private final String nomeBandeiraCartao;

    public ListagemBandeiraCartaoDto(BandeiraCartaoEntity bandeiraCartao) {
        this.idBandeiraCartao = bandeiraCartao.getIdBandeiraCartao();
        this.nomeBandeiraCartao = bandeiraCartao.getNomeBandeiraCartao();
    }
}
