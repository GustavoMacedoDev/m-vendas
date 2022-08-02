package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraAtividadeEconomicaDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.AtividadeEconomicaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/atividade-economica")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Atividade Economica")
public class AtividadeEconomicaResource {

    @Inject
    AtividadeEconomicaService atividadeEconomicaService;

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Atividade Economica", description = "Cadastra Atividade Economica")
    public Response cadastraAtividadeEconomica(@Valid @NotNull(message = "A requisição não pode ser nula")
                                               CadastraAtividadeEconomicaDto cadastraAtividadeEconomicaDto) {

        MensagemResposta mensagemResposta = atividadeEconomicaService.cadastraAtividadeEconomica(cadastraAtividadeEconomicaDto);
        return null;
    }
}
