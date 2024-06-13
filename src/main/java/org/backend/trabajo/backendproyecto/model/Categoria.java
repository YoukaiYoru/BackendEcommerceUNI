package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private int idCategoria;
    private String categoriaTipo;
    private String categoriaDescripcion;
    private int categoriaContador;

        //RELATIONS
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> listProductos;

        //GETTERS AND SETTERS
    public Categoria() {}

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoriaTipo() {
        return categoriaTipo;
    }

    public void setCategoriaTipo(String categoriaTipo) {
        this.categoriaTipo = categoriaTipo;
    }

    public String getCategoriaDescripcion() {
        return categoriaDescripcion;
    }

    public void setCategoriaDescripcion(String categoriaDescripcion) {
        this.categoriaDescripcion = categoriaDescripcion;
    }

    public int getCategoriaContador() {
        return categoriaContador;
    }

    public void setCategoriaContador(int categoriaContador) {
        this.categoriaContador = categoriaContador;
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }
}
