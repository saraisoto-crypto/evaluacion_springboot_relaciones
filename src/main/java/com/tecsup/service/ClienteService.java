package com.tecsup.service;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() { return repo.findAll(); }

    public Cliente obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    public Cliente guardar(Cliente c) { return repo.save(c); }

    public Cliente actualizar(Long id, Cliente c) {
        obtener(id);
        c.setId(id);
        return repo.save(c);
    }

    public void eliminar(Long id) {
        obtener(id);
        repo.deleteById(id);
    }
}