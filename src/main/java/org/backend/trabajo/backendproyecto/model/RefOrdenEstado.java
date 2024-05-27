package org.backend.trabajo.backendproyecto.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Ref_orden_estado")
public class RefOrdenEstado {
    @Id
    @GeneratedValue
    private long idRefOrdenEstado;


    private String  ordenEstadoDescrip;
}
