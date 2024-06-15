package org.backend.trabajo.backendproyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdenDetalles;
    private int cantidadProducto;
    private float subTotalPrecio;
    //ORDERS

    @JoinColumn(name = "id_orden")
    @ManyToOne(cascade = CascadeType.ALL)
    private Orden orden;

    @JoinColumn(name ="id_producto")
    @ManyToOne(cascade = CascadeType.ALL)
    private Producto producto;

}