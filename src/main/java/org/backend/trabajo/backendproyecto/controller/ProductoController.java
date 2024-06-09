package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/categoria/{categoria}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable String categoria) {
        return ProductServicio.obtenerProductosPorCategoria(categoria);
    }

    @PostMapping("/add")
    public void agregarProducto(@RequestBody DatosProductoDTO productoDTO) {
        ProductServicio.agregarProducto(productoDTO);
    }
}
