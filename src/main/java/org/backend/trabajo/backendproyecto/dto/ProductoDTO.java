package org.backend.trabajo.backendproyecto.dto;

import org.backend.trabajo.backendproyecto.model.Categoria;

public record ProductoDTO(
        Long id_product,
        String product_name,
        String product_description,
        float product_price,
        int product_stock,
        String product_img_url
        ){
}
