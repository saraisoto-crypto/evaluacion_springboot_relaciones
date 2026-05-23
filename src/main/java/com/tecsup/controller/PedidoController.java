package com.tecsup.controller;

import com.tecsup.model.Pedido;
import com.tecsup.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Pedido obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    public ResponseEntity<Pedido> registrar(@RequestBody Pedido pedido) {
        return ResponseEntity.status(201).body(service.registrar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}