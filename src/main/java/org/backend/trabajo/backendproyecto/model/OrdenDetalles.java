package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_detalles")
public class OrdenDetalles {

    @Id
    private int idOrderDetails;
    private int productPrice;
    private int qtyProduct;
    //ORDERS

    //GETTERS AND SETTERS
    public OrdenDetalles() {}
}