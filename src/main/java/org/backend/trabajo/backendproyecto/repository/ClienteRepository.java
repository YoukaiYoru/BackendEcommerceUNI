package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByClientUser(String usr);

}
