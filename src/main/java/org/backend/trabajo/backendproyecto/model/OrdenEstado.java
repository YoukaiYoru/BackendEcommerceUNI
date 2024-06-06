package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_estado")
public class OrdenEstado {

    @Id
    @GeneratedValue
    private int idOrderStatus;
    private String orderStatusName;

    //RELATIONS

    @OneToOne
    @JoinColumn(name = "id_orden_estado")
    private Orden orden;


    //GETTERS AND SETTERS

    public OrdenEstado() {}



}
