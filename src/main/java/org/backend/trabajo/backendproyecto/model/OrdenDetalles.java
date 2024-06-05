package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    private int id_order_details;
    private int product_price;
    private int qty_product;

        //ORDERS
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Producto producto;

        //GETTERS AND SETTERS
    public int getId_order_details() { return id_order_details; }

    public void setId_order_details(int id_order_details) { this.id_order_details = id_order_details; }

    public int getProduct_price() { return product_price; }

    public void setProduct_price(int product_price) { this.product_price = product_price; }

    public int getQty_product() { return qty_product; }

    public void setQty_product(int qty_product) { this.qty_product = qty_product; }

    public Orden getOrden() { return orden; }

    public void setOrden(Orden orden) { this.orden = orden; }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto) { this.producto = producto; }
}
