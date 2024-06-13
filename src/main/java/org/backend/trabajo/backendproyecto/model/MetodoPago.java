package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "metodo_pago")
public class MetodoPago {

    @Id
    private int id_metodo_pago;
    private String metodo_pago;

    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Orden> orden;

//GETTERS AND SETTERS
public MetodoPago() {}

    public int getId_metodo_pago() {
        return id_metodo_pago;
    }

    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public List<Orden> getOrden() {
        return orden;
    }

    public void setOrden(List<Orden> orden) {
        this.orden = orden;
    }
}
