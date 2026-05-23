package com.tecsup.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auditoria_log")
public class AuditoriaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accion;
    private String metodo;
    private Timestamp fecha;
    private String usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
}