package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    private Long idProduct;

    private String productName;
    private String productDescription;
    private float productPrice;
    private int productStock;
    private String productImgUrl;


    //RELATIONS

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Categoria categoria;

    //TO STRING
    @Override
    public String toString() {
        return "Producto{" +
                "idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productImgUrl='" + productImgUrl + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    //GETTERS AND SETTERS
    public Producto() {}

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
