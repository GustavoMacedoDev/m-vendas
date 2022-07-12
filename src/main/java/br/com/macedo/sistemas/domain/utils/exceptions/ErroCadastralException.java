package br.com.macedo.sistemas.domain.utils.exceptions;

import javax.ws.rs.BadRequestException;

public class ErroCadastralException extends BadRequestException {

    public ErroCadastralException(String msg) {
        super(msg);
    }

}
