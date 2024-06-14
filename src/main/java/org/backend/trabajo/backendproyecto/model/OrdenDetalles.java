package org.backend.trabajo.backendproyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdenDetalles;
    private int cantidadProducto;
    private float subTotalPrecio;
    //ORDERS

    @JoinColumn(name = "id_orden")
    @ManyToOne(cascade = CascadeType.ALL)
    private Orden orden;

    @JoinColumn(name ="id_producto")
    @ManyToOne(cascade = CascadeType.ALL)
    private Producto producto;

    //GETTERS AND SETTERS
    public OrdenDetalles() {}

    public float getSubTotalPrecio() {
        return subTotalPrecio;
    }

    public void setSubTotalPrecio(@NotNull Producto producto) {
        this.subTotalPrecio = producto.getProductPrice() * cantidadProducto;
    }

    public int getIdOrdenDetalles() {
        return idOrdenDetalles;
    }

    public void setIdOrdenDetalles(int idOrdenDetalles) {
        this.idOrdenDetalles = idOrdenDetalles;
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