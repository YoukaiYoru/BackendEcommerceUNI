package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrdenRepository extends JpaRepository<Orden, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.clientUser =: clientUser ")
    List<Orden> findByClientUser(String clientUser);
}
