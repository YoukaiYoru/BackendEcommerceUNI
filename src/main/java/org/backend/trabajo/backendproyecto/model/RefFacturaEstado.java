package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ref_factura_estado")
public class RefFacturaEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_Estado")
    private long factEstado;

    @Column(name = "fact_estado_descrip")
    private String factEstadoDescripcion;

    @OneToOne
    @JoinColumn(name = "factura_num")
    private Factura factura;


    public RefFacturaEstado(long factEstado, String factEstadoDescripcion) {
        this.factEstado = factEstado;
        this.factEstadoDescripcion = factEstadoDescripcion;
    }

    public RefFacturaEstado() {}

    public long getFactEstado() {
        return factEstado;
    }

    public void setFactEstado(long factEstado) {
        this.factEstado = factEstado;
    }

    public String getFactEstadoDescripcion() {
        return factEstadoDescripcion;
    }

    public void setFactEstadoDescripcion(String factEstadoDescripcion) {
        this.factEstadoDescripcion = factEstadoDescripcion;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "RefFacturaEstado{" +
                "factEstado=" + factEstado +
                ", factEstadoDescripcion='" + factEstadoDescripcion + '\'' +
                ", factura=" + factura +
                '}';
    }
}
