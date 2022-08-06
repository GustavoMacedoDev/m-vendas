package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastraCategoriaProdutoDto implements Serializable {
    private String nomeCategoriaProduto;
}
