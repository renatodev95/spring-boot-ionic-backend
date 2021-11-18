package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.Cidade;
import com.renatodev.cursomc.domain.Cliente;
import com.renatodev.cursomc.domain.Endereco;
import com.renatodev.cursomc.domain.enums.TipoCliente;
import com.renatodev.cursomc.dto.ClienteDTO;
import com.renatodev.cursomc.dto.ClienteNewDTO;
import com.renatodev.cursomc.repositories.ClienteRepository;
import com.renatodev.cursomc.repositories.EnderecoRepository;
import com.renatodev.cursomc.services.exceptions.DataIntegrityException;
import com.renatodev.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente cliente) {
        newObj.setNome(cliente.getNome());
        newObj.setEmail(cliente.getEmail());
    }

    public void deleteById(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
}
