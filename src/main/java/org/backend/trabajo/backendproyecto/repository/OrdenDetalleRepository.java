package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalles, Integer> {

    @Modifying
    List<OrdenDetalles> findByOrden_IdOrdenAndProducto_IdProductoAndOrden_Cliente_IdClient(Long idOrden, Long idProducto, Long idCliente);

}
