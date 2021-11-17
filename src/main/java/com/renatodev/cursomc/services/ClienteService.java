package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Cliente;
import com.renatodev.cursomc.repositories.ClienteRepository;
import com.renatodev.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
    }
}
