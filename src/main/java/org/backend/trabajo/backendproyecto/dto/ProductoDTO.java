package org.backend.trabajo.backendproyecto.dto;

public record ProductoDTO(
        Long idProducto,
        String prod_nombre,
        Double prod_precio,
        String prod_descripcion,
        String prod_imagen) {
}
