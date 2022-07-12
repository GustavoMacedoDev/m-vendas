package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraNaturezaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListagemNaturezaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.NaturezaJuridicaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/natureza-juridica")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Natureza Juridica")
public class NaturezaJuridicaResource {

    @Inject
    NaturezaJuridicaService naturezaJuridicaService;

    @GET
    @Path("/")
    @Operation(summary = "Lista as naturezas juridicas", description = "Lista as naturezas juridicas")
    public Response listaNaturezaJuridicas() {
        List<ListagemNaturezaJuridicaDto> listaNaturezaJuridica = naturezaJuridicaService.listaNaturezaJuridica();

        return Response.status(Response.Status.OK).entity(listaNaturezaJuridica).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Natureza Jurídica", description = "Cadastra Natureza Jurídica")
    public Response cadastraNaturezaJuridica(@Valid @NotNull(message = "A requisição não pode ser nula")
                                             CadastraNaturezaJuridicaDto cadastraNaturezaJuridicaDto) {

        MensagemResposta mensagemResposta = naturezaJuridicaService.cadastraNaturezaJuridica(cadastraNaturezaJuridicaDto);

        return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();


    }

}
