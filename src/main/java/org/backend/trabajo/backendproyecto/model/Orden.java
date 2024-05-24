package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Orden {
    @Id
    private Long id;
    private String orden_details;
    private LocalDate orden_dia;

    public Orden() {}
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
