package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Cliente;
import com.renatodev.cursomc.domain.Pedido;
import com.renatodev.cursomc.repositories.PedidoRepository;
import com.renatodev.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
    }
}
