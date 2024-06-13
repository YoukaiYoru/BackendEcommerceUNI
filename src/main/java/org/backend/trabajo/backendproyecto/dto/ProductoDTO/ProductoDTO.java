package org.backend.trabajo.backendproyecto.dto.ProductoDTO;

public record ProductoDTO(
        Long id_product,
        String product_name,
        String product_description,
        float product_price,
        int product_stock,
        String product_img_url,
        String categoria_producto
        ){


}
