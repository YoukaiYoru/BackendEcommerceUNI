package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    private int idOrdenDetalles;
    private int productoPrecio;
    private int cantidadProducto;
    //ORDERS

    @ManyToOne
    @JoinColumn(name = "id_orden")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name ="id_producto")
    private Producto producto;

    //GETTERS AND SETTERS
    public OrdenDetalles() {}

    public int getIdOrdenDetalles() {
        return idOrdenDetalles;
    }

    public void setIdOrdenDetalles(int idOrdenDetalles) {
        this.idOrdenDetalles = idOrdenDetalles;
    }

    public int getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(int productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}