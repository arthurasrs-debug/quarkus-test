package br.com.curso.produtos.service;

import java.util.List;

import br.com.curso.produtos.dto.ProdutoRequest;
import br.com.curso.produtos.dto.ProdutoResponse;
import br.com.curso.produtos.exception.ProdutoNaoEncontradoException;
import br.com.curso.produtos.model.Produto;
import br.com.curso.produtos.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped 
public class ProdutoService {
    
    @Inject 
    ProdutoRepository repository;

    public List<ProdutoResponse> listar(){
        return repository.listar()
            .stream()
            .map(this::converterParaResponse)
            .toList();
    }

    public ProdutoResponse buscarPorId(Long id){
        Produto produto = repository.buscarPorId(id)
        .orElseThrow(() -> 
        new ProdutoNaoEncontradoException(id)
        );

        return converterParaResponse(produto);
    }

    public ProdutoResponse cadastrar(ProdutoRequest request){
       Produto produto = new Produto(
        null,
        request.nome(),
        request.descricao(),
        request.preco()
       );
       
       Produto salvo = repository.salvar(produto);

       return converterParaResponse(salvo);
    }

    public ProdutoResponse atualizar(
        Long id,
        ProdutoRequest request
    ){
        repository.buscarPorId(id)
            .orElseThrow(() ->
                new ProdutoNaoEncontradoException(id)
        );

        Produto produto = new Produto(
            id,
            request.nome(),
            request.descricao(),
            request.preco()
        );

        Produto atualizado = repository.atualizar(produto);
        
        return converterParaResponse(atualizado); 
    }

    public void excluir(Long id){
        repository.buscarPorId(id)
            .orElseThrow(() ->
                new ProdutoNaoEncontradoException(id)
        );

        repository.excluir(id);
    }

    private ProdutoResponse converterParaResponse(
        Produto produto
    ){
        return new ProdutoResponse(
            produto.id(),
            produto.nome(),
            produto.descricao(),
            produto.preco()
        );
    }
}
