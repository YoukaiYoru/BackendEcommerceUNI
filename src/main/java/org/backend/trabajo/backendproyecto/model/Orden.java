package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    private Long idOrder;
    private float order_amount;
    private LocalDate order_date;
    private LocalDate date_delivery;

        //ORDERS
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "orden_estado")
    private OrdenEstado ordenEstado;


    @OneToMany(mappedBy = "orden")
    private List<OrdenDetalles> ordenDetallesList;

        //GETTERS AND SETTERS

    public Orden() {}

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public float getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(float order_amount) {
        this.order_amount = order_amount;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public LocalDate getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(LocalDate date_delivery) {
        this.date_delivery = date_delivery;
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



    public List<OrdenDetalles> getOrdenDetallesList() {
        return ordenDetallesList;
    }

    public void setOrdenDetallesList(List<OrdenDetalles> ordenDetallesList) {
        this.ordenDetallesList = ordenDetallesList;
    }
}
