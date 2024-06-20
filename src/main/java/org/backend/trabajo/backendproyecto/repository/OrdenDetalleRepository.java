package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalles, Integer> {

    @Modifying
    /*
    List<OrdenDetalles> findByOrden_IdOrdenAndProducto_IdProductoAndOrden_Cliente_IdClient(Long idOrden, Long idProducto, Long idCliente);}
    */
    Optional<OrdenDetalles> findByOrdenAndProducto(Orden orden, Producto producto);

    List<OrdenDetalles> findByOrden(Orden orden);
}
