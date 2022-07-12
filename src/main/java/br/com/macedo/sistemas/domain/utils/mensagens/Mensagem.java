package br.com.macedo.sistemas.domain.utils.mensagens;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mensagem;

}
