package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import org.backend.trabajo.backendproyecto.dto.DatosProductoDTO;

import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String productName;
    private String productDescription;
    private float productPrice;
    private int productStock;
    private String productImgUrl;


    //RELATIONS

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "producto")
    private List<OrdenDetalles> ordenDetalles;

    // TO STRING

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productImgUrl='" + productImgUrl + '\'' +
                ", categoria=" + categoria +
                ", ordenDetalles=" + ordenDetalles +
                '}';
    }

    //CONSTRUCTORES
    public Producto() {}

    public Producto(DatosProductoDTO datosProductoDTO){
        this.productName = datosProductoDTO.product_name();
        this.productDescription = datosProductoDTO.product_description();
        this.productPrice = datosProductoDTO.product_price();
        this.productStock = datosProductoDTO.product_stock();
        this.productImgUrl = datosProductoDTO.product_img_url();
    }

    //GETTERS AND SETTERS
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public List<OrdenDetalles> getOrdenDetalles() {
        return ordenDetalles;
    }

    public void setOrdenDetalles(List<OrdenDetalles> ordenDetalles) {
        this.ordenDetalles = ordenDetalles;
    }

}
