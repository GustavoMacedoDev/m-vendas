package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraAtividadeEconomicaDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.awt.geom.RectangularShape;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/atividade-economica")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Atividade Economica")
public class AtividadeEconomicaResource {

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Atividade Economica", description = "Cadastra Atividade Economica")
    public Response cadastraAtividadeEconomica(@Valid @NotNull(message = "A requisição não pode ser nula")
                                               CadastraAtividadeEconomicaDto cadastraAtividadeEconomicaDto) {
        return null;
    }
}
