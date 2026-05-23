package com.tecsup.service;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.exception.StockInsuficienteException;
import com.tecsup.model.*;
import com.tecsup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private ProductoRepository productoRepo;
    @Autowired private ClienteRepository clienteRepo;

    public List<Pedido> listar() { return pedidoRepo.findAll(); }

    public Pedido obtener(Long id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
    }

    @Transactional
    public Pedido registrar(Pedido pedido) {
        Cliente cliente = clienteRepo.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDate.now());

        double total = 0;
        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = productoRepo.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            if (producto.getStock() < detalle.getCantidad())
                throw new StockInsuficienteException("Stock insuficiente: " + producto.getNombre());

            double subtotal = detalle.getCantidad() * producto.getPrecio();
            detalle.setSubtotal(subtotal);
            detalle.setProducto(producto);
            detalle.setPedido(pedido);
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepo.save(producto);
            total += subtotal;
        }

        pedido.setTotal(total);
        return pedidoRepo.save(pedido);
    }

    public void eliminar(Long id) {
        obtener(id);
        pedidoRepo.deleteById(id);
    }
}