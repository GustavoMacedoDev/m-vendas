package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.ListaBairroDto;
import br.com.macedo.sistemas.service.BairroService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/bairro")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Bairros")
public class BairroResource {

    @Inject
    BairroService bairroService;

    @GET
    @Path("/")
    @Operation(summary = "Lista Bairros", description = "Lista Bairros")
    public Response listaBairros() {

        ListaBairroDto listaBairro = bairroService.listaBairro();

        return Response.status(Response.Status.OK).entity(listaBairro).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Bairro", description = "Cadastra Bairro")
    public Response cadastraBairro() {
        return null;
        
    }
}
