package br.com.macedo.sistemas.domain.enums;

import lombok.Getter;

@Getter
public enum StatusProdutoEnum {

    ATIVO(1),
    INATIVO(2);

    private int codigo;

    StatusProdutoEnum(int codigo) {
        this.codigo = codigo;
    }

}
