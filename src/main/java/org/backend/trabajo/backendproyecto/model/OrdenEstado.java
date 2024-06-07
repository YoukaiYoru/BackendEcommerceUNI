package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orden_estado")
public class OrdenEstado {

    @Id
    private int idOrderStatus;
    private String orderStatusName;

    //RELATIONS

    @OneToMany(mappedBy = "ordenEstado")
    private List<Orden> orden;


    //GETTERS AND SETTERS

    public OrdenEstado() {}

    public int getIdOrderStatus() {
        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public List<Orden> getOrden() {
        return orden;
    }

    public void setOrden(List<Orden> orden) {
        this.orden = orden;
    }
}
