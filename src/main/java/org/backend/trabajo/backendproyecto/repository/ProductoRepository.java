package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByProd_nombre(String nombre);

    @Query("select m from Producto p WHERE p.prod_nombre ILIKE %:nombre%")
    List<Producto> buscaProductosPorNombre(String nombre);
}
