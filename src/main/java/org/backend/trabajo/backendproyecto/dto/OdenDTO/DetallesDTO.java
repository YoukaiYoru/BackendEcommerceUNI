package org.backend.trabajo.backendproyecto.dto.OdenDTO;

public record DetallesDTO (
        Long idProducto,
        String nombreProducto,
        Integer cantidadProducto,
        Float precioTotal
){
}
