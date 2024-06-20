package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.DatosProductoDTO;

import java.util.List;


@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String productName;
    @Column(length = 2500)
    private String productDescription;
    private float productPrice;
    private int productStock;
    private String productImgUrl;


    //RELATIONS

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "producto", orphanRemoval = true)
    private List<OrdenDetalles> ordenDetalles;
    // TO STRING

    public Producto(){};
    public Producto(DatosProductoDTO datosProductoDTO){
        this.productName = datosProductoDTO.product_name();
        this.productDescription = datosProductoDTO.product_description();
        this.productPrice = datosProductoDTO.product_price();
        this.productStock = datosProductoDTO.product_stock();
        this.productImgUrl = datosProductoDTO.product_img_url();
    }



}
