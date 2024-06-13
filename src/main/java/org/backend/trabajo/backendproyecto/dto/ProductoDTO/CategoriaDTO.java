package org.backend.trabajo.backendproyecto.dto.ProductoDTO;

public record CategoriaDTO(
        int idCategoria,
        String categoriaTipo,
        String categoriaDescripcion,
        int categoriaContador
) {
}
