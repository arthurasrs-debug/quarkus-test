package br.com.curso.produtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/produtos")
public class ProdutoResource {

    @GET
    public List<Produto> listar(){
        return List.of(
            new Produto(1L, "notebook", new BigDecimal("1999.50")),
            new Produto(2L, "PS5", new BigDecimal("3500.00")),
            new Produto(3L, "Xbox Series S", new BigDecimal("2299.99"))
        );
    }

    
    public record Produto(
        Long id,
        String nome,
        BigDecimal preco
    ){}
    
}
