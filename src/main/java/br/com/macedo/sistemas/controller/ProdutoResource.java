package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.AlteraDadosProdutoDto;
import br.com.macedo.sistemas.domain.dto.CadastraProdutoDto;
import br.com.macedo.sistemas.domain.dto.ListaProdutoPorIdDto;
import br.com.macedo.sistemas.domain.dto.ListagemProdutoDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/produtos")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Produto")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/")
    public Response listaProdutos() {
        List<ListagemProdutoDto> listagemProdutoDto = produtoService.listaProdutos();

        return Response.status(Response.Status.OK).entity(listagemProdutoDto).build();
    }

    @GET
    @Path("/produto/{idProduto}")
    public Response listaProdutoPorId(@Parameter(description = "Código do Produto", required = true)
                                      @PathParam("idProduto") Long idProduto) {
        ListaProdutoPorIdDto listaProduto  = produtoService.buscaProdutoPorId(idProduto);

        return Response.status(Response.Status.OK).entity(listaProduto).build();
    }


    @POST
    @Path("/")
    public Response cadastrarProduto(@Valid @NotNull CadastraProdutoDto cadastraProdutoDto) {

        MensagemResposta mensagemResposta = produtoService.cadastrarProduto(cadastraProdutoDto);

        return Response.status(Response.Status.OK).entity(mensagemResposta).build();
    }

    @PUT
    @Path("/{idProduto}")
    public Response alteraDadosProduto( @Parameter(description = "Código do Produto", required = true)
                                        @PathParam("idProduto") Long idProduto,
                                        @Valid @NotNull(message = "A requisição não pode ser nula")
                                        AlteraDadosProdutoDto alteraDadosProdutoDto) {
        MensagemResposta mensagemResposta = produtoService.alteraDadosProduto(idProduto, alteraDadosProdutoDto);

        return Response.status(Response.Status.OK).entity(mensagemResposta).build();
    }

}
