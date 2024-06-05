package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private int id_category;
    private String category_type;
    private String category_description;
    private int category_count;

        //ORDERS
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productoList;

        //GETTERS AND SETTERS
    public int getId_category() { return id_category; }

    public void setId_category(int id_category) { this.id_category = id_category; }

    public String getCategory_type() { return category_type; }

    public void setCategory_type(String category_type) { this.category_type = category_type; }

    public String getCategory_description() { return category_description; }

    public void setCategory_description(String category_description) { this.category_description = category_description; }

    public int getCategory_count() { return category_count;     }

    public void setCategory_count(int category_count) { this.category_count = category_count; }

    public List<Producto> getProductoList() { return productoList; }

    public void setProductoList(List<Producto> productoList) { this.productoList = productoList; }

}
