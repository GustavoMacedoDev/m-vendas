package br.com.macedo.sistemas.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListagemBandeiraCartaoDto implements Serializable {
    private List<String> nomeBandeiraCartao;
}
