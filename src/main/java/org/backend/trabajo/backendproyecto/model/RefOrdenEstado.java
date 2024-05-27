package org.backend.trabajo.backendproyecto.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class RefOrdenEstado {
    @Id
    @GeneratedValue
    private long idRefOrdenEstado;


    private String  ordenEstadoDescrip;
}
