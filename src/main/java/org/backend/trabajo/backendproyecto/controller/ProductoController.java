package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.DatosRespuestaProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/get")
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity obtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO productoById = productoService.obtenerPorId(id);
        return ResponseEntity.ok(productoById);
    }

    @GetMapping("/get/categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoria);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/delete")
    public ResponseEntity eliminarProductoPorId(@RequestParam Long id) {
        productoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/id/{id}/precio/{precio}")
    @Transactional
    public ResponseEntity actualizarPrecioPorId(@PathVariable Long id, @PathVariable Float precio) {
        Producto productoActualizado = productoService.actualizarPrecioPorId(id, precio);

        if (productoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        DatosRespuestaProductoDTO respuestaDTO = new DatosRespuestaProductoDTO(
                productoActualizado.getProductName(),
                productoActualizado.getProductDescription(),
                productoActualizado.getProductPrice(),
                productoActualizado.getProductStock(),
                productoActualizado.getProductImgUrl(),
                productoActualizado.getCategoria().getCategoriaDescripcion()
        );

        return ResponseEntity.ok(respuestaDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<Producto> agregarProducto(@RequestParam("nombre") String nombre,
                                                    @RequestParam("precio") float price,
                                                    @RequestParam("descripcion") String descripcion,
                                                    @RequestParam("cantidad") int cantidad,
                                                    @RequestParam("categoriaNombre") String categoriaNombre,
                                                    @RequestParam("imagen") MultipartFile imagen) {
        try {
            Producto producto = productoService.agregarProducto(nombre, price, descripcion, cantidad, categoriaNombre, imagen);
            return ResponseEntity.ok(producto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
