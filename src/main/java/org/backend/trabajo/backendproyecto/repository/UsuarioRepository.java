package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUsrLoginIs(String usrLogin);


}
