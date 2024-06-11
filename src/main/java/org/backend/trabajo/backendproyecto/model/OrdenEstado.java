package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orden_estado")
public class OrdenEstado {

    @Id
    private int idOrdenEstado;
    private String ordenEstadoNombre;

    //RELATIONS

    @OneToMany(mappedBy = "ordenEstado")
    private List<Orden> orden;


    //GETTERS AND SETTERS

    public OrdenEstado() {}

    public int getIdOrdenEstado() {
        return idOrdenEstado;
    }

    public void setIdOrdenEstado(int idOrdenEstado) {
        this.idOrdenEstado = idOrdenEstado;
    }

    public String getOrdenEstadoNombre() {
        return ordenEstadoNombre;
    }

    public void setOrdenEstadoNombre(String ordenEstadoNombre) {
        this.ordenEstadoNombre = ordenEstadoNombre;
    }

    public List<Orden> getOrden() {
        return orden;
    }

    public void setOrden(List<Orden> orden) {
        this.orden = orden;
    }

}
