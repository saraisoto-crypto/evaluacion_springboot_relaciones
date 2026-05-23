package com.tecsup.controller;

import com.tecsup.model.Producto;
import com.tecsup.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto p) {
        return ResponseEntity.status(201).body(service.guardar(p));
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @Valid @RequestBody Producto p) {
        return service.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}