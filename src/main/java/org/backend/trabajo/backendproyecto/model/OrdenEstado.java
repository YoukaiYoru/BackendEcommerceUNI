package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "orden_estado")
public class OrdenEstado {

    @Id
    private int idOrdenEstado;
    private String ordenEstadoNombre;

    //RELATIONS

    @OneToMany(mappedBy = "ordenEstado", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orden> orden;


}
