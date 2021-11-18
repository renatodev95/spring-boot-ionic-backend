package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Categoria;
import com.renatodev.cursomc.dto.CategoriaDTO;
import com.renatodev.cursomc.repositories.CategoriaRepository;
import com.renatodev.cursomc.services.exceptions.DataIntegrityException;
import com.renatodev.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return repo.findByOrderByNomeAsc();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public void update(Categoria categoria) {
        find(categoria.getId());
        repo.save(categoria);
    }

    public void deleteById(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public Categoria fromDto(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}
