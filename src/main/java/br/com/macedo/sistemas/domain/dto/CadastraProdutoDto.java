package br.com.macedo.sistemas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastraProdutoDto implements Serializable {
    private String nomeProduto;
    private BigDecimal valor;
    private Set<CategoriaProdutoDto> categorias = new HashSet<>();
}
