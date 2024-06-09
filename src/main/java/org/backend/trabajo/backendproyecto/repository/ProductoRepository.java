package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Categoria;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
}