package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    private int id_order;
    private float order_amount;
    private LocalDate order_date;
    private LocalDate date_delivery;

        //ORDERS
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "orden_estado")
    private OrdenEstado ordenEstado;

    @OneToOne
    @JoinColumn(name = "metodo_pago")
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "orden")
    private List<OrdenDetalles> ordenDetallesList;

        //GETTERS AND SETTERS
    public int getId_order() { return id_order; }

    public void setId_order(int id_order) { this.id_order = id_order; }

    public float getOrder_amount() { return order_amount; }

    public void setOrder_amount(float order_amount) { this.order_amount = order_amount; }

    public LocalDate getOrder_date() { return order_date; }

    public void setOrder_date(LocalDate order_date) { this.order_date = order_date; }

    public LocalDate getDate_delivery() { return date_delivery; }

    public void serDate_delivery(LocalDate date_delivery) { this.date_delivery = date_delivery; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public OrdenEstado getOrdenEstado() { return ordenEstado; }

    public void setOrdenEstado(OrdenEstado ordenEstado) { this.ordenEstado = ordenEstado; }

    public MetodoPago getMetodoPago() { return metodoPago; }

    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public List<OrdenDetalles> getOrdenDetallesList() { return ordenDetallesList; }

    public void setOrdenDetallesList(List<OrdenDetalles> ordenDetallesList) { this.ordenDetallesList = ordenDetallesList; }

    public Orden() {}
}
