package br.com.curso.produtos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoRequest (
    @NotBlank(message = "Nome obrigatório") 
    @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
    String nome,

    @Size(max = 500, message = "a descricao deve ter no maximo 500 caracteres")
    String descricao,

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que 0")
    BigDecimal preco
){}
