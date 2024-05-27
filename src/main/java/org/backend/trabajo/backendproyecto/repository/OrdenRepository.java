package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
