package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;


    private String clientUser;
    private String clientPassword;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhone;



    //RELATIONS
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Orden> listOrden;




}
