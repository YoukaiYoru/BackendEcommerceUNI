package org.backend.trabajo.backendproyecto.repository;

import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByClientUser(String usr);
    Cliente findByIdClient(Long idCliente);
    Optional<Cliente> findByClientUserAndClientEmail(String usr, String email);
}
