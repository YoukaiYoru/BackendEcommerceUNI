package org.backend.trabajo.backendproyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private float orderAmount;
    private LocalDate orderDate;
    private LocalDate dateDelivery;



    //ORDERS

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_orden_estado")
    private OrdenEstado ordenEstado;

    @OneToMany(mappedBy = "orden")
    private List<OrdenDetalles> ordenDetalles;


    //TO STRING

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden=" + idOrden +
                ", orderAmount=" + orderAmount +
                ", orderDate=" + orderDate +
                ", dateDelivery=" + dateDelivery +
                ", cliente=" + cliente +
                ", metodoPago=" + metodoPago +
                ", ordenEstado=" + ordenEstado +
                ", ordenDetalles=" + ordenDetalles +
                '}';
    }
//GETTERS AND SETTERS

    public Orden() {}

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<OrdenDetalles> getOrdenDetalles() {
        return ordenDetalles;
    }

    public void setOrdenDetalles(List<OrdenDetalles> ordenDetalles) {
        this.ordenDetalles = ordenDetalles;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
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
