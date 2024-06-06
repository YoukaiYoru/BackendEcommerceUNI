package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    private Long idOrder;
    private float orderAmount;
    private LocalDate orderDate;
    private LocalDate dateDelivery;



    //ORDERS

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orden")
    private OrdenEstado ordenEstado;


    //TO STRING

    @Override
    public String toString() {
        return "Orden{" +
                "idOrder=" + idOrder +
                ", orderAmount=" + orderAmount +
                ", orderDate=" + orderDate +
                ", dateDelivery=" + dateDelivery +
                ", cliente=" + cliente +
                '}';
    }


    //GETTERS AND SETTERS

    public Orden() {}

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrdenEstado getOrdenEstado() {
        return ordenEstado;
    }

    public void setOrdenEstado(OrdenEstado ordenEstado) {
        this.ordenEstado = ordenEstado;
    }
}
