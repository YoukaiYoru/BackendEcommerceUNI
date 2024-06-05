package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_estado")
public class OrdenEstado {

    @Id
    @GeneratedValue
    private int id_order_status;
    private String order_status_name;
}
