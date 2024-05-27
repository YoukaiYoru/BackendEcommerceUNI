package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ref_forma_de_pago")
public class RefFormaDePago {
    @Id
    @GeneratedValue
    @Column(name = "id_forma_de_pago")
    private int idRefFormaDePago;


    private String formaPagoDescripcion;
}
