package br.com.curso.produtos.exception;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider 
public class ProdutoExceptionMapper 
    implements ExceptionMapper<ProdutoNaoEncontradoException> {
    
        @Override
        public Response toResponse(
            ProdutoNaoEncontradoException exception
        ){
            return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of(
                    "status", 404,
                    "erro", "Produto não encontrado",
                    "mensagem", exception.getMessage()
                ))
                .build();
        }
}