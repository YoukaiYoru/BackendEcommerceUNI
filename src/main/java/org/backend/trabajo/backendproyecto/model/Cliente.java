package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue
    private int id_client;
    private String client_user;
    private String client_password;
    private String client_firstName;
    private String client_lastName;
    private String client_email;
    private String client_phone;

        //ORDERS
    @OneToMany(mappedBy = "cliente")
    private List<Orden> ordenList;

        //GETTERS AND SETTERS
    public int getId_client(){ return id_client; }

    public void setId_client(int id_client){ this.id_client = id_client;}

    public String getClient_user(){ return client_user; }

    public void setClient_user(String client_user){ this.client_user = client_user; }

    public String getClient_password(){ return client_password; }

    public void setClient_password(String client_password){ this.client_password = client_password; }

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
