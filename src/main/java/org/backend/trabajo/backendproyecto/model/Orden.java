package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private float ordenMonto;
    private LocalDate ordenDate;
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
                ", ordenMonto=" + ordenMonto +
                ", ordenDate=" + ordenDate +
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

    public float getOrdenMonto() {
        return ordenMonto;
    }

    public void setOrdenMonto(float ordenMonto) {
        this.ordenMonto = ordenMonto;
    }

    public LocalDate getOrdenDate() {
        return ordenDate;
    }

    public void setOrdenDate(LocalDate ordenDate) {
        this.ordenDate = ordenDate;
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
