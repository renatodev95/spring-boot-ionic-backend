package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Categoria;
import com.renatodev.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }
}
