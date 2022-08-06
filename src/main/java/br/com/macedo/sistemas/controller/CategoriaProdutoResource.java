package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.AlteraDadosCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.CadastraCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.dto.ListagemCategoriaProdutoDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.CategoriaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
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

@Path("/categoria_produto")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Categoria de Produtos")
public class CategoriaProdutoResource {

    @Inject
    CategoriaService categoriaProdutoService;

    @GET
    @Path("")
    @Operation(description = "Lista Categorias", summary = "Lista Categorias")
    public Response listaCategoriasProdutos() {
        List<ListagemCategoriaProdutoDto> listagemCategoriaProdutoDto = categoriaProdutoService.listaCategorias();

        return Response.status(Response.Status.OK).entity(listagemCategoriaProdutoDto).build();
    }

    @GET
    @Path("/id/{idCategoriaProduto}")
    @Operation(description = "Busca categoria pelo Id", summary = "Busca Categoria pelo Id")
    public Response listaCategoriaPeloId(@Parameter(description = "Código da Categoria", required = true)
                                         @PathParam("idCategoriaProduto") Long idCategoria) {
        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = categoriaProdutoService.listaCategoriaPeloId(idCategoria);

        return Response.status(Response.Status.OK).entity(detalhaCategoriaProdutoDto).build();

    }

    @GET
    @Path("/nome/{nomeCategoriaProduto}")
    @Operation(description = "Busca categoria pelo Nome", summary = "Busca Categoria pelo Nome")
    public Response listaCategoriaPeloNome(@Parameter(description = "Nome da Categoria", required = true)
                                         @PathParam("nomeCategoriaProduto") String nomeCategoria) {
        DetalhaCategoriaProdutoDto detalhaCategoriaProdutoDto = categoriaProdutoService.listaCategoriaPeloNome(nomeCategoria);

        return Response.status(Response.Status.OK).entity(detalhaCategoriaProdutoDto).build();

    }

    @POST
    @Path("")
    @Operation(description = "Cadastro de Categoria", summary = "Cadastro de Categoria")
    public Response cadastraNovaCategoria(@NotNull @Valid CadastraCategoriaProdutoDto cadastraCategoriaProdutoDto) {
        System.out.println(cadastraCategoriaProdutoDto.getNomeCategoriaProduto());
        MensagemResposta mensagemResposta = categoriaProdutoService.cadastraCategoria(cadastraCategoriaProdutoDto);

        return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();
    }

    @PUT
    @Path("/{idCategoria}")
    @Operation(description = "Altera dados da categoria", summary = "Altera dados da categoria")
    public Response alteraDadosCategoria(@Parameter(description = "Código da Categoria", required = true)
                                         @PathParam("idCategoria") Long idCategoria,
                                         @Valid @NotNull(message = "A requisição não pode ser nula")
                                         AlteraDadosCategoriaProdutoDto alteraDadosCategoriaProdutoDto) {
        MensagemResposta mensagemResposta = categoriaProdutoService.alteraDadosCategoria(idCategoria, alteraDadosCategoriaProdutoDto);

        return Response.status(Response.Status.OK).entity(mensagemResposta).build();

    }


}
