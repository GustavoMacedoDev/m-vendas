package br.com.macedo.sistemas.domain.utils.exceptions;

import br.com.macedo.sistemas.domain.utils.mensagens.Mensagem;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralException implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {

        Mensagem mensagem = new Mensagem();

        if(e instanceof ErroCadastralException) {
            mensagem.setMensagem(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
        }

        mensagem.setMensagem("Erro ao executar transação");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
    }
}
