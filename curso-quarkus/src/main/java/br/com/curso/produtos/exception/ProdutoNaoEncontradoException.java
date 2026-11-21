package br.com.curso.produtos.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id){
        super("Produto não encontrado co ID: " + id);
    }
}
