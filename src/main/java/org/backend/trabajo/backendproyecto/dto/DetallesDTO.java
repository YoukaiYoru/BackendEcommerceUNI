package org.backend.trabajo.backendproyecto.dto;

public record DetallesDTO (
        Long idProducto,
        String nombreProducto,
        Integer cantidadProducto,
        Float precioTotal
){
}
