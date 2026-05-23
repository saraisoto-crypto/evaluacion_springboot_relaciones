package com.tecsup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetallePedido> detalles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
}