package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    private int idPago;
    private Date fechaPago;
    private double valorPago;
    public Pago() {}


}
