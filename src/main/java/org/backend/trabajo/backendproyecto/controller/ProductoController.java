package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRespuestaProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity obtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO productoById = productoService.obtenerPorId(id);
        return ResponseEntity.ok(productoById);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoria);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/add")
    public ResponseEntity<DatosRespuestaProductoDTO> agregarProducto(@Valid @RequestBody DatosProductoDTO datosProducto,
                                                                     UriComponentsBuilder uriBuilder) {
        Long productoId = productoService.agregarProducto(datosProducto);
        DatosRespuestaProductoDTO r = new DatosRespuestaProductoDTO(datosProducto.product_name(), datosProducto.product_description(),
                datosProducto.product_price(), datosProducto.product_stock(), datosProducto.product_img_url(), datosProducto.categoria_producto());
        URI url = uriBuilder.path("/producto/id/{id}").buildAndExpand(productoId).toUri();
        return ResponseEntity.created(url).body(r);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarProductoPorId(@PathVariable Long id) {
        productoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/change/{id}/{precio}")
    public ResponseEntity actualizarPrecioPorId(@PathVariable Long id, @PathVariable Float precio) {
        Producto productoActualizado = productoService.actualizarPrecioPorId(id, precio);
        DatosRespuestaProductoDTO r = new DatosRespuestaProductoDTO(productoActualizado.getProductName(),
                productoActualizado.getProductDescription(),
                productoActualizado.getProductPrice(),
                productoActualizado.getProductStock(),
                productoActualizado.getProductImgUrl(),
                productoActualizado.getCategoria().getCategoriaDescripcion()
                );
        return ResponseEntity.ok(r);
    }

}
