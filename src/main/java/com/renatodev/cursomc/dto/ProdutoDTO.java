package com.renatodev.cursomc.dto;

import com.renatodev.cursomc.domain.Produto;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Produto prod) {
        id = prod.getId();
        nome = prod.getNome();
        preco = prod.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
