package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService ProductServicio;


    @GetMapping()
    public List<ProductoDTO> obtenerProductos() {
        return ProductServicio.obtenerTodosLosProductos();
    }


    @GetMapping("/{id}")
    public ProductoDTO obtenerPorId(@PathVariable Long id) {
        return ProductServicio.obtenerPorId(id);
    }


}
