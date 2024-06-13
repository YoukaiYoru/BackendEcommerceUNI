package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import org.backend.trabajo.backendproyecto.dto.ClienteDTO.DatosRegistroClienteDTO;

import java.util.List;

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
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Orden> listOrden;



    //TO STRING

    @Override
    public String toString() {
        return "Cliente{" +
                "idClient=" + idClient +
                ", clientUser='" + clientUser + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", listOrden=" + listOrden +
                '}';
    }

    // CONSTRUCTOR
    public Cliente() {

    }

    public Cliente(DatosRegistroClienteDTO datosCliente) {
        this.clientUser = datosCliente.clientUser();
        this.clientPassword = datosCliente.clientPassword();
        this.clientFirstName = datosCliente.clientFirstName();
        this.clientLastName = datosCliente.clientLastName();
        this.clientEmail = datosCliente.clientEmail();
        this.clientPhone = datosCliente.clientPhone();
    }


    // GETTERS AND SETTERS

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getClientUser() {
        return clientUser;
    }

    public void setClientUser(String clientUser) {
        this.clientUser = clientUser;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public List<Orden> getListOrden() {
        return listOrden;
    }

    public void setListOrden(List<Orden> listOrden) {
        this.listOrden = listOrden;
    }

}
