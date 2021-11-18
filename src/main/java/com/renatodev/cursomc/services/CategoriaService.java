package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Categoria;
import com.renatodev.cursomc.repositories.CategoriaRepository;
import com.renatodev.cursomc.services.exceptions.DataIntegrityException;
import com.renatodev.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId());
        return repo.save(categoria);
    }

    public void deleteById(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }
}
