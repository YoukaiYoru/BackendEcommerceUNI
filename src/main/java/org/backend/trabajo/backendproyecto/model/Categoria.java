package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
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


}
