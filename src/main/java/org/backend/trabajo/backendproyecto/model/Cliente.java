package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;

import lombok.Data;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.DatosRegistroClienteDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements UserDetails {

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

    public Cliente(DatosRegistroClienteDTO datosRegistroClienteDTO) {
    }

    public Cliente() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return clientPassword;
    }

    @Override
    public String getUsername() {
        return clientUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
