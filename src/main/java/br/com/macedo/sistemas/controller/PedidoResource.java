package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.dto.CadastraPedidoDto;
import br.com.macedo.sistemas.domain.dto.DetalhaPedidoDto;
import br.com.macedo.sistemas.domain.dto.ListaPedidosDto;
import br.com.macedo.sistemas.domain.utils.mensagens.MensagemResposta;
import br.com.macedo.sistemas.service.PedidoService;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pedidos")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Tag(name = "Pedidos")
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @POST
    @Path("")
    public Response cadastraPedido(CadastraPedidoDto cadastraPedidoDto) {
        MensagemResposta mensagemResposta = pedidoService.cadastraPedido(cadastraPedidoDto);

        return Response.status(Response.Status.CREATED).entity(mensagemResposta).build();

    }

    @GET
    @Path("")
    public Response listaPedidos() {
        List<ListaPedidosDto> listaPedidosDto = pedidoService.listaPedidos();

        return Response.status(Response.Status.OK).entity(listaPedidosDto).build();
    }

    @GET
    @Path("/{idPedido}")
    public Response listaPedidoPorId(@Parameter(description = "Código do Pedido", required = true)
                                     @PathParam("idPedido") Long idPedido) {
        DetalhaPedidoDto detalhaPedidoDto = pedidoService.detalhaPedido(idPedido);

        return Response.status(Response.Status.OK).entity(detalhaPedidoDto).build();
    }
}
