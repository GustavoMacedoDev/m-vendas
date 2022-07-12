package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.AlteraTipoEnderecoDto;
import br.com.macedo.sistemas.domain.dto.CadastraTipoEnderecoDto;
import br.com.macedo.sistemas.domain.dto.ListaTipoEnderecoDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.TipoEnderecoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/tipoendereco")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Tipo de Endereço")
public class TipoEnderecoResource {

    @Inject
    TipoEnderecoService tipoEnderecoService;

    @GET
    @Path("/")
    @Operation(summary = "Lista todos tipos de enderecos", description = "Lista todos tipos de Endereços")
    public Response listaTodosTiposEndereco() {
        List<ListaTipoEnderecoDto> listaTipoEnderecoDto = tipoEnderecoService.listaTipoEndereco();

        return Response.status(Response.Status.OK).entity(listaTipoEnderecoDto).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastra novo tipo de endereço", description = "Cadastra novo tipo de endereço")
    public Response cadastraNovoTipoEndereco(@Valid @NotNull(message = "A requisição não pode ser nula")
                                             CadastraTipoEnderecoDto cadastraTipoEnderecoDto) {
        MensagemResposta mensagemResposta = tipoEnderecoService.cadastraTipoEndereco(cadastraTipoEnderecoDto);

        return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();

    }

    @PUT
    @Path("/{codigoTipoEndereco}")
    @Operation(summary = "Editar tipo de endereço", description = "Editar tipo de Endereço")
    public Response alterarTipoEndereco(@Parameter(description = "Código do tipo de endereço", required = true)
                                        @PathParam("codigoTipoEndereco") Long codigoTipoEndereco,
                                        @Valid @NotNull(message = "A requisição não pode ser nula")
                                        AlteraTipoEnderecoDto alteraTipoEnderecoDto) {

        MensagemResposta mensagemResposta = tipoEnderecoService.alteraTipoEndereco(codigoTipoEndereco, alteraTipoEnderecoDto);

        return Response.status(Response.Status.OK).entity(mensagemResposta).build();

    }

    @DELETE
    @Path("/{codigoTipoEndereco}")
    @Operation(summary = "Deleta Tipo de Endereço", description = "Deleta Tipo de Endereço")
    public Response deletaTipoEndereco(@Parameter(description = "Código do tipo de endereço", required = true)
                                       @PathParam("codigoTipoEndereco") Long codigoTipoEndereco) {

        MensagemResposta mensagemResposta = tipoEnderecoService.deletaTipoEndereco(codigoTipoEndereco);

        return Response.status(Response.Status.OK).entity(mensagemResposta).build();

    }


}
