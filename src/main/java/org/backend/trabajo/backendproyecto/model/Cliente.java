package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue
    
    private Long idClient;
    private String clientUser;
    private String clientPassword;
    private String client_firstName;
    private String client_lastName;
    private String client_email;
    private String client_phone;

        //ORDERS
    @OneToMany(mappedBy = "cliente")
    private List<Orden> ordenList;

        //GETTERS AND SETTERS
    public Long getId_client(){ return idClient; }

    public void setId_client(Long id_client){ this.idClient = id_client;}

    public String getClientUser(){ return clientUser; }

    public void setClientUser(String clientUser){ this.clientUser = clientUser; }

    public String getClientPassword(){ return clientPassword; }

    public void setClientPassword(String clientPassword){ this.clientPassword = clientPassword; }

    public String getClient_firstName(){ return client_firstName; }

    public void setClient_firstName(String client_firstName){ this.client_firstName = client_firstName; }

    public String getClient_lastName(){ return client_lastName; }

    public void setClient_lastName(String client_lastName){ this.client_lastName = client_lastName; }

    public String getClient_email(){ return client_email; }

    public void setClient_email(String client_email){ this.client_email = client_email; }

    public String getClient_phone(){ return client_phone; }

    public void setClient_phone(String client_phone){ this.client_phone = client_phone;}

    public List<Orden> getOrdenList(){ return ordenList; }

    public void setOrdenList(List<Orden> ordenList){ this.ordenList = ordenList; }
}
