package org.backend.trabajo.backendproyecto.dto;

public record CategoriaDTO(
        int idCategoria,
        String categoriaTipo,
        String categoriaDescripcion,
        int categoriaContador
) {
}
