package com.renatodev.cursomc.dto;

import com.renatodev.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CategoriaDTO {

    private Long id;
    @NotEmpty(message = "preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "o tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
