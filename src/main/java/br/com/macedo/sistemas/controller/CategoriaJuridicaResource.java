package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraCategoriaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListagemCategoriaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.CategoriaJuridicaService;
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

@Path("/categoria-juridica")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Categoria Juridica")
public class CategoriaJuridicaResource {

    @Inject
    CategoriaJuridicaService categoriaJuridicaService;

    @GET
    @Path("/")
    @Operation(summary = "Lista todas as Categorias Jurídicas", description = "Lista todas as Categorias Jurídicas")
    public Response listaTodasCategorias() {
        List<ListagemCategoriaJuridicaDto> listaCategorias = categoriaJuridicaService.listaTodasCategorias();

        return Response.status(Response.Status.OK).entity(listaCategorias).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastra Nova Categoria Juridica", description = "Cadastra Nova Categoria Juridica")
    public Response cadastraCategoriaJuridica(@Valid @NotNull(message = "A requisição não pode ser nula")
                                              CadastraCategoriaJuridicaDto cadastraCategoriaJuridicaDto) {

        MensagemResposta mensagemResposta = categoriaJuridicaService.cadastraCategoriaJuridica(cadastraCategoriaJuridicaDto);

        return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();
    }
}
