package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public void agregarProducto(@RequestBody DatosProductoDTO productoDTO) {
        ProductServicio.agregarProducto(productoDTO);
    }


}
