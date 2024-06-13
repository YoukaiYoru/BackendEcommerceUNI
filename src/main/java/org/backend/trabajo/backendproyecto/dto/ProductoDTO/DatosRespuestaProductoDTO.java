package org.backend.trabajo.backendproyecto.dto.ProductoDTO;

import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaProductoDTO(
        @NotBlank
        String product_name,
        @NotBlank
        String product_description,
        @NotBlank
        float product_price,
        @NotBlank
        int product_stock,
        String product_img_url,
        @NotBlank
        String categoria_producto
) {


}

