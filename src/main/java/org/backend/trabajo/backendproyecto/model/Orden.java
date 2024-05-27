package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Orden {
    @Id
    private Long id;
    private String orden_details;
    private LocalDate orden_dia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    private String orden_estado;


    public Orden() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrden_details() {
        return orden_details;
    }

    public void setOrden_details(String orden_details) {
        this.orden_details = orden_details;
    }

    public LocalDate getOrden_dia() {
        return orden_dia;
    }

    public void setOrden_dia(LocalDate orden_dia) {
        this.orden_dia = orden_dia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getOrden_estado() {
        return orden_estado;
    }

    public void setOrden_estado(String orden_estado) {
        this.orden_estado = orden_estado;
    }
}
