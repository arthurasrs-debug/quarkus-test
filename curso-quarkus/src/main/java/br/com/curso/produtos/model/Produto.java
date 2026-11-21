package br.com.curso.produtos.model;

import java.math.BigDecimal;

public record Produto(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco
) {}
