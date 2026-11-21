package br.com.curso.produtos.dto;

import java.math.BigDecimal;

public record ProdutoResponse (
    Long id,
    String nome,
    String descricao,
    BigDecimal preco
){}
