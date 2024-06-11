package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.dto.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.service.OrdenDetalleService;
import org.backend.trabajo.backendproyecto.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;
    @Autowired
    private OrdenDetalleService ordenDetalleService;

    @GetMapping()
    public List<OrdenDTO> obtenerProductos() {
        return ordenService.obtenerTodasLasOrdenes();
    }

    @GetMapping("/user/{login}")
    public List<OrdenDTO> obtenerOrdenPorUsr(@PathVariable String login ) {
        return ordenService.obtenerPorClientUsr(login);
    }

    @GetMapping("/{id}")
    public List<OrdenDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return Collections.singletonList(ordenService.obtenerPorId(id));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<TodasLasOrdenesDTO>> getDetailedOrdenInfoAsJson() {
        List<TodasLasOrdenesDTO> r = ordenService.obtenerOrdenesDetallasDeClientes();
        return ResponseEntity.ok(r);
    }

    @Transactional
    @DeleteMapping("/{userId}/{orderId}/detalles/{orderDetailsId}")
    public ResponseEntity<String> eliminarProductoDeOrdenDetalles(@PathVariable Long userId,
                                                                  @PathVariable Long orderId,
                                                                  @PathVariable Long orderDetailsId) {
        try {
            ordenDetalleService.eliminarProductoDeOrdenDetalles(userId, orderId, orderDetailsId);
            return ResponseEntity.ok("Producto eliminado de la orden exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
