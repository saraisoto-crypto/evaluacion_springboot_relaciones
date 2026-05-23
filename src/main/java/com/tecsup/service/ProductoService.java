package com.tecsup.service;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() { return repo.findAll(); }

    public Producto obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Producto guardar(Producto p) { return repo.save(p); }

    public Producto actualizar(Long id, Producto p) {
        obtener(id);
        p.setId(id);
        return repo.save(p);
    }

    public void eliminar(Long id) {
        obtener(id);
        repo.deleteById(id);
    }
}