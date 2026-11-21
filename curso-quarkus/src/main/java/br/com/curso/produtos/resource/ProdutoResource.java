package br.com.curso.produtos.resource;

import java.util.List;

import br.com.curso.produtos.dto.ProdutoRequest;
import br.com.curso.produtos.dto.ProdutoResponse;
import br.com.curso.produtos.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @Inject 
    ProdutoService service;

    @Inject 
    UriInfo uriInfo;

    @GET 
    public List<ProdutoResponse> listar(){
        return service.listar();
    }

    @GET 
    @Path("/{id}")
    public ProdutoResponse buscarPorId(
        @PathParam("id") Long id
    ){
        return service.buscarPorId(id);
    }

    @POST 
    public Response cadastrar(
        @Valid ProdutoRequest request
    ){
        ProdutoResponse produto = service.cadastrar(request);

        return Response.created(
            uriInfo.getAbsolutePathBuilder()
                .path(produto.id().toString())
                .build()
        )
        .entity(produto)
        .build();
    }

    @PUT 
    @Path("/{id}")
    public ProdutoResponse atualizar(
        @PathParam("id") Long id,
        @Valid ProdutoRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DELETE 
    @Path("/{id}")
    public Response excluir(
        @PathParam("id") Long id
    ){
        service.excluir(id);
        
        return Response.noContent().build();
    }
}
