package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    UserDetails findByUsuario(String username);
}
