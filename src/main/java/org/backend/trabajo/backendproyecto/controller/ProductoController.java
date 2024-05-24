package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/producto")
    public List<ProductoDTO> obtenerProductos() {
        return productoRepository.findAll().stream()
                .map(p -> new ProductoDTO(p.getProd_nombre(),p.getProd_descripcion(), p.getProd_imagen()))
                .collect(Collectors.toList());
    }
}
