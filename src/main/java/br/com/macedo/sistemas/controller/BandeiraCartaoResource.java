package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.ListagemBandeiraCartaoDto;
import br.com.macedo.sistemas.service.BandeiraCartaoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/bandeira-cartao")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Bandeira Cartão")
public class BandeiraCartaoResource {

    @Inject
    BandeiraCartaoService bandeiraCartaoService;

    @GET
    @Path("/")
    @Operation(summary = "Lista Bandeiras", description = "Lista Bandeiras")
    public Response listaBandeiras() {
        List<ListagemBandeiraCartaoDto> bandeiras = bandeiraCartaoService.listaBandeiras();

        return Response.status(Response.Status.OK).entity(bandeiras).build();
    }
}
