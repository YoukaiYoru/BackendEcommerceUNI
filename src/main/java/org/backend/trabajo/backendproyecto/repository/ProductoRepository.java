package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}