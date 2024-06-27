package org.backend.trabajo.backendproyecto.dto.OrdenDTO;

public record DetallesDTO (
        Long idProducto,
        String nombreProducto,
        Integer cantidadProducto,
        Float precioTotal
){
}
