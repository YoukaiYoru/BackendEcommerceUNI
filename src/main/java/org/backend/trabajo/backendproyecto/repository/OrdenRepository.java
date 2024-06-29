package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrdenRepository extends JpaRepository<Orden, Long> {

    @Query("SELECT o FROM Orden o WHERE o.cliente.clientUser = :clientUser")
    List<Orden> findByClientUser(@Param("clientUser") String clientUser);

    @Query("SELECT c.idClient, c.clientEmail, c.clientFirstName, c.clientLastName, c.clientPhone, c.clientUser, " +
            "o.idOrden, o.dateDelivery, o.ordenDate, o.ordenMonto, mp.metodo_pago, " +
            "oe.ordenEstadoNombre, p.idProducto, p.productImgUrl, p.productName, p.productPrice, " +
            "od.cantidadProducto, od.subTotalPrecio " +
            "FROM Cliente c " +
            "JOIN c.listOrden o " +
            "JOIN o.ordenDetalles od " +
            "JOIN o.ordenEstado oe " +
            "JOIN od.producto p " +
            "JOIN o.metodoPago mp")
    List<Object[]> findDetailedOrdenInfo();


    List<Orden> findByIdOrden(Long idOrden);
}
