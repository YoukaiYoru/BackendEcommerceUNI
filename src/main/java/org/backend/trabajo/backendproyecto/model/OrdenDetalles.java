package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdenDetalles;
    private float productoPrecio;
    private int cantidadProducto;
    private float subTotalPrecio;
    //ORDERS

    @JoinColumn(name = "id_orden")
    @ManyToOne(targetEntity = Orden.class)
    private Orden orden;

    @JoinColumn(name ="id_producto")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Producto producto;

    //GETTERS AND SETTERS
    public OrdenDetalles() {}

    public float getSubTotalPrecio() {
        return subTotalPrecio;
    }

    public void setSubTotalPrecio(Producto producto) {
        this.subTotalPrecio = producto.getProductPrice() * cantidadProducto;
    }

    public int getIdOrdenDetalles() {
        return idOrdenDetalles;
    }

    public void setIdOrdenDetalles(int idOrdenDetalles) {
        this.idOrdenDetalles = idOrdenDetalles;
    }

    public float getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(Long productoPrecio) {
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