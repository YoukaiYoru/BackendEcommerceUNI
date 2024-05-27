package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrdenRepository extends JpaRepository<Orden, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario =: idUsaurio ")
    List<Orden> findByIdUsr(long idUsuario);
}
