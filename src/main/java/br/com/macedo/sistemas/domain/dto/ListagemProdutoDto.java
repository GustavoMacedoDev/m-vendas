package br.com.macedo.sistemas.domain.dto;

import br.com.macedo.sistemas.domain.aggregate.CategoriaProdutoEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ListagemProdutoDto implements Serializable {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal valorAtual;
    private Set<CategoriaProdutoEntity> categorias = new HashSet<>();
}
