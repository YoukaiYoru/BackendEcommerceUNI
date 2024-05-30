package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Orden")
public class Orden {
    @Id
    private Long id;
    private String orden_details;
    private LocalDate orden_dia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "ref_orden_estado")
    private RefOrdenEstado ref_orden_estado;



    public Orden() {}

    public RefOrdenEstado getRef_orden_estado() {
        return ref_orden_estado;
    }

    public void setRef_orden_estado(RefOrdenEstado ref_orden_estado) {
        this.ref_orden_estado = ref_orden_estado;
    }

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

}
