package org.backend.trabajo.backendproyecto.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_num")
    long fact_num;





}
