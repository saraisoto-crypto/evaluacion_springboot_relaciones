package com.tecsup.controller;

import com.tecsup.model.Categoria;
import com.tecsup.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<Categoria> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@Valid @RequestBody Categoria c) {
        return ResponseEntity.status(201).body(service.guardar(c));
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id, @Valid @RequestBody Categoria c) {
        return service.actualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}