package org.backend.trabajo.backendproyecto.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_num")
    private long factNum;

    private Date factDay;
    private String factDetalles;

    @OneToOne
    @JoinColumn(name = "fact_estado")
    private RefFacturaEstado refFacturaEstado;

    @OneToOne
    @JoinColumn(name = "id_orden")
    private Orden orden;


    public long getFactNum() {
        return factNum;
    }

    public void setFactNum(long factNum) {
        this.factNum = factNum;
    }

    public Date getFactDay() {
        return factDay;
    }

    public void setFactDay(Date factDay) {
        this.factDay = factDay;
    }

    public String getFactDetalles() {
        return factDetalles;
    }

    public void setFactDetalles(String factDetalles) {
        this.factDetalles = factDetalles;
    }

    public RefFacturaEstado getRefFacturaEstado() {
        return refFacturaEstado;
    }

    public void setRefFacturaEstado(RefFacturaEstado refFacturaEstado) {
        this.refFacturaEstado = refFacturaEstado;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
