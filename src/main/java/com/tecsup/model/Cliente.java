package com.tecsup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String nombres;
    @NotBlank private String apellidos;

    @Email
    @Column(unique = true)
    private String correo;

    @NotBlank private String telefono;
    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Pedido> pedidos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}