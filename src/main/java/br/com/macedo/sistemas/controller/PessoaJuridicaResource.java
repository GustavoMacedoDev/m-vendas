package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraPessoaJuridicaDto;
import br.com.macedo.sistemas.domain.dto.ListaPessoaJuridicaDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.PessoaJuridicaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
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

@Path("/pessoajuridica")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Pessoa Jurídica")
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaService pjService;

    @GET
    @Path("/")
    @RolesAllowed("Funcionario")
    @Operation(summary = "Lista todas as pessoas jurídicas", description = "Lista todos Pessoas Jurídicas")
    public Response listarPessoaJuridica() {

        List<ListaPessoaJuridicaDto> listaPessoaJuridicaDto =pjService.listaPessoaJuridica();

        return Response.status(Response.Status.OK).entity(listaPessoaJuridicaDto).build();
    }

    @POST
    @Path("/")
    @Operation(summary = "Cadastro de Pessoa Jurídica", description = "Cadastro de Pessoa Jurídica")
    public Response cadastraPessoaJuridica(@Valid @NotNull(message = "A requisição não pode ser nula")
                                                   CadastraPessoaJuridicaDto cadastraPessoaJuridicaDto) {

        //MensagemResposta mensagemResposta = pjService.validaCadastroPessoaJuridica(cadastraPessoaJuridicaDto);

        //return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();

        return null;
    }
}
