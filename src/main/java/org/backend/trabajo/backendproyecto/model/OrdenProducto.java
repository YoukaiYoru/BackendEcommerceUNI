package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orden_prod")
public class OrdenProducto {
    @Id
    private long idOrdenProd;
    private long idProducto;
    private long idOrden;
    private long ordenProdEstatus;
    private int ordenProdCant;
    private double ordenProdPrecio;
    public OrdenProducto() {}
}
