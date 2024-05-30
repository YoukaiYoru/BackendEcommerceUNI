package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue
    private Long idUsuario;
    private String usrNombre;
    private String usrApellido;
    private String usrCorreo;
    private String usrLogin;
    private String usrContrasenia;
    private String usrTelefono;
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

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrApellido() {
        return usrApellido;
    }

    public void setUsrApellido(String usrApellido) {
        this.usrApellido = usrApellido;
    }

    public String getUsrCorreo() {
        return usrCorreo;
    }

    public void setUsrCorreo(String usrCorreo) {
        this.usrCorreo = usrCorreo;
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrContrasenia() {
        return usrContrasenia;
    }

    public void setUsrContrasenia(String usrContrasenia) {
        this.usrContrasenia = usrContrasenia;
    }

    public String getUsrTelefono() {
        return usrTelefono;
    }

    public void setUsrTelefono(String usrTelefono) {
        this.usrTelefono = usrTelefono;
    }


    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }
}
