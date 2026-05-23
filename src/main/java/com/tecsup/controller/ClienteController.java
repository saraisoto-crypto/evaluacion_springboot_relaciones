package com.tecsup.controller;

import com.tecsup.model.Cliente;
import com.tecsup.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente c) {
        return ResponseEntity.status(201).body(service.guardar(c));
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody Cliente c) {
        return service.actualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}