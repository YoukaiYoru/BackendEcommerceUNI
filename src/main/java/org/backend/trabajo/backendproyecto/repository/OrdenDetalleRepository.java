package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalles, Integer> {

    @Modifying
    @Query("DELETE from OrdenDetalles where producto.idProducto=:idProducto")
    void deleteByIdProductInOrder(Long idProducto);


}
