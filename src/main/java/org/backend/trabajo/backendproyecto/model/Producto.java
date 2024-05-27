package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    private Long idProducto;
    private String prod_nombre;
    private String prod_descripcion;
    private String prod_imagen;


    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", prod_nombre='" + prod_nombre + '\'' +
                ", prod_descripcion='" + prod_descripcion + '\'' +
                ", prod_imagen='" + prod_imagen + '\'' +
                '}';
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProd_nombre() {
        return prod_nombre;
    }

    public void setProd_nombre(String prod_nombre) {
        this.prod_nombre = prod_nombre;
    }

    public String getProd_descripcion() {
        return prod_descripcion;
    }

    public void setProd_descripcion(String prod_descripcion) {
        this.prod_descripcion = prod_descripcion;
    }

    public String getProd_imagen() {
        return prod_imagen;
    }

    public void setProd_imagen(String prod_imagen) {
        this.prod_imagen = prod_imagen;
    }

    public Producto() {}
}
