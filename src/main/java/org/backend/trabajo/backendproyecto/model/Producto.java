package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    private int id_product;
    private String product_name;
    private String product_description;
    private float product_price;
    private int product_stock;
    private String product_img_url;

    @Override
    public String toString() {
        return "producto{" +
                "id_product=" + id_product + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_stock='" + product_stock + '\'' +
                ", product_img_url='" + product_img_url + '\'' +
                '}';
    }

        //ORDERS
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<OrdenDetalles> OrdenDatalles;

        //GETTERS AND SETTERS
    public int getId_product() { return id_product; }

    public void setId_product(int id_product) { this.id_product = id_product;}

    public String getProduct_name() { return product_name; }

    public void setProduct_name(String product_name) { this.product_name = product_name;}

    public String getProduct_description() { return product_description; }

    public void setProduct_description(String product_description) { this.product_description = product_description;}

    public float getProduct_price() { return product_price; }

    public void setProduct_price(float product_price) { this.product_price = product_price; }

    public int getProduct_stock() { return product_stock;}

    public void setProduct_stock(int product_stock) { this.product_stock = product_stock;}

    public String getProduct_img_url() { return product_img_url;}

    public void setProduct_img_url(String product_img_url) { this.product_img_url = product_img_url;}

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Producto() {}
}
