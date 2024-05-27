package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import org.backend.trabajo.backendproyecto.model.Orden;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue
    private long idUsuario;
    private String usr_nombre;
    private String usr_apellido;
    private String usr_correo;
    private String usr_login;
    private String usr_contrasenia;
    private String usr_telefono;
    // Ordenes
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenList;

//Getters and Setters
    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsr_nombre() {
        return usr_nombre;
    }

    public void setUsr_nombre(String usr_nombre) {
        this.usr_nombre = usr_nombre;
    }

    public String getUsr_apellido() {
        return usr_apellido;
    }

    public void setUsr_apellido(String usr_apellido) {
        this.usr_apellido = usr_apellido;
    }

    public String getUsr_correo() {
        return usr_correo;
    }

    public void setUsr_correo(String usr_correo) {
        this.usr_correo = usr_correo;
    }

    public String getUsr_login() {
        return usr_login;
    }

    public void setUsr_login(String usr_login) {
        this.usr_login = usr_login;
    }

    public String getUsr_contrasenia() {
        return usr_contrasenia;
    }

    public void setUsr_contrasenia(String usr_contrasenia) {
        this.usr_contrasenia = usr_contrasenia;
    }

    public String getUsr_telefono() {
        return usr_telefono;
    }

    public void setUsr_telefono(String usr_telefono) {
        this.usr_telefono = usr_telefono;
    }


    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }
}
