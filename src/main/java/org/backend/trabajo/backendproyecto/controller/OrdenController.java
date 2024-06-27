package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.OrdenDTO.OrdenDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
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
    public List<OrdenDTO> obtenerOrdenes() {
        return ordenService.obtenerTodasLasOrdenes();
    }

    @GetMapping("/user/{login}")
    public List<OrdenDTO> obtenerOrdenPorUsr(@RequestParam String login ) {
        return ordenService.obtenerPorClientUsr(login);
    }

    @GetMapping("/{id}")
    public List<OrdenDTO> obtenerOrdenPorId(@RequestParam Long id) {
        return Collections.singletonList(ordenService.obtenerPorId(id));
    }


    @GetMapping("/todas")
    public ResponseEntity<List<TodasLasOrdenesDTO>> obtenerTodasLasOrdenes() {
        List<TodasLasOrdenesDTO> r = ordenService.obtenerUsuariosYOrdenes();
        return ResponseEntity.ok(r);
    }

    @PostMapping("/crear")
    public ResponseEntity<Orden> crearOrdenInicial(@RequestParam Long idCliente) {
        try {
            Orden orden = ordenService.crearOrdenInicial(idCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Orden> finalizarOrden(@RequestParam Long idOrden, @RequestParam int idMetodoPago) {
        try {
            Orden orden = ordenService.finalizarOrden(idOrden, idMetodoPago);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/editarEstado")
    public ResponseEntity<Orden> editarEstadoOrden(@RequestParam Long idOrden, @RequestParam String ordenEstado) {
        try {
            Orden orden = ordenService.editarEstadoOrden(idOrden, ordenEstado);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/agregarProducto")
    public ResponseEntity<OrdenDetalles> agregarProductosAOrdenDetalles(@RequestParam Long idProducto, @RequestParam Long idOrden, @RequestParam int cantidadPedidaProducto) {
        try {
            OrdenDetalles ordenDetalles = ordenDetalleService.agregarProductosAOrdenDetalles(idProducto, idOrden, cantidadPedidaProducto);
            return ResponseEntity.status(HttpStatus.CREATED).body(ordenDetalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/modificarCantidadProducto")
    public ResponseEntity<OrdenDetalles> modificarCantidadDeProductosAOrdenDetalles(@RequestParam Long idOrden, @RequestParam Long idProducto, @RequestParam int nuevaCantidadPedidaProducto) {
        try {
            OrdenDetalles ordenDetalles = ordenDetalleService.modificarCantidadDeProductosAOrdenDetalles(idOrden, idProducto, nuevaCantidadPedidaProducto);
            return ResponseEntity.ok(ordenDetalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
