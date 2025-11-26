package com.IFSP.api_produtos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.IFSP.api_produtos.model.Produto;
import com.IFSP.api_produtos.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping
    public List<Produto> listar(){
        return repository.findAll();
    } 

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto novoProduto){
        return repository.findById(id).map(produto ->{
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            return repository.save(produto);
        }).orElse(null);
    }
}
