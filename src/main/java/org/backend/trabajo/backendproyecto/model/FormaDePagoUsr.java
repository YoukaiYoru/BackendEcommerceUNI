package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Forma_pago_usr")
public class FormaDePagoUsr {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private long idFormaDePago;
    private String FormaPagoDetalles;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;



}
