package br.com.curso.produtos.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import br.com.curso.produtos.model.Produto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository {

    private final ConcurrentHashMap<Long, Produto> produtos = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(0);

    public List<Produto> listar(){
        return produtos.values()
        .stream()
        .sorted((a, b) -> a.id().compareTo(b.id()))
        .toList();
    }

    public Optional<Produto> buscarPorId(Long id){
        return Optional.ofNullable(produtos.get(id));
    }

    public Produto salvar(Produto produto){
        Long id = sequence.incrementAndGet();

        Produto novoProduto = new Produto(
            id,
            produto.nome(),
            produto.descricao(),
            produto.preco()
        );

        produtos.put(id, novoProduto);
        return  novoProduto;
    }

    public Produto atualizar(Produto produto){
        produtos.put(produto.id(), produto);
        return produto;
    }

    public void excluir(Long id){
        produtos.remove(id);
    }
    
}
