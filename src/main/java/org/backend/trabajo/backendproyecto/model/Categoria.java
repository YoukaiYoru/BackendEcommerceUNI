package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private int idCategory;
    private String categoryType;
    private String categoryDescription;
    private int categoryCount;

        //RELATIONS
    @OneToMany(mappedBy = "categoria")
    private List<Producto> listProductos;

        //GETTERS AND SETTERS
    public Categoria() {}
}
