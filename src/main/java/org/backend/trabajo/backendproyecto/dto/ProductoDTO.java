package org.backend.trabajo.backendproyecto.dto;

public record ProductoDTO(
        Long idProducto,
        String prod_nombre,
        String prod_descripcion,
        String prod_imagen) {
}
