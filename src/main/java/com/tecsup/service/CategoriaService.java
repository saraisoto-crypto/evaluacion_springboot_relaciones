package com.tecsup.service;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Categoria;
import com.tecsup.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listar() { return repo.findAll(); }

    public Categoria obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
    }

    public Categoria guardar(Categoria c) { return repo.save(c); }

    public Categoria actualizar(Long id, Categoria c) {
        obtener(id);
        c.setId(id);
        return repo.save(c);
    }

    public void eliminar(Long id) {
        obtener(id);
        repo.deleteById(id);
    }
}