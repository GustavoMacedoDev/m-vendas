package br.com.macedo.sistemas.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ListagemCategoriaProdutoDto implements Serializable {
    private Long idCategoria;
    private String nomeCategoria;
}
