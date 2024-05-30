package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    private Long idProducto;
    private String prodNombre;
    private Double prodPrecio;
    private String prodDescripcion;
    private String prodImagen;
    private Long prodCantidad;


    @Override
        public String toString() {
            return "Producto{" +
                    "idProducto=" + idProducto +
                    ", prodNombre='" + prodNombre + '\'' +
                    ", prodDescripcion='" + prodDescripcion + '\'' +
                    ", prodImagen='" + prodImagen + '\'' +
                    ", prodPrecio=" + prodPrecio + '\'' +
                    ", prodCantidad=" + prodCantidad + '\'' +
                    '}';
        }


        //GETTERS AND SETTERS

    public Long getProdCantidad() {
        return prodCantidad;
    }

    public void setProdCantidad(Long prodCantidad) {
        this.prodCantidad = prodCantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public String getProdImagen() {
        return prodImagen;
    }

    public void setProdImagen(String prodImagen) {
        this.prodImagen = prodImagen;
    }

    public Double getProdPrecio() {
        return prodPrecio;
    }

    public void setProdPrecio(Double prodPrecio) {
        this.prodPrecio = prodPrecio;
    }

    public Producto() {}
}
