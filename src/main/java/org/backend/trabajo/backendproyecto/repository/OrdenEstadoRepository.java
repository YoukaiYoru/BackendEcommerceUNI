package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.OrdenEstado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdenEstadoRepository extends JpaRepository<OrdenEstado, Integer> {
    Optional<OrdenEstado> findByOrdenEstadoNombre(String ordenEstadoNombre);
}


