package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.OrdenAndDetailDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.OrdenDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.TodasLasOrdenesDTO;
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
import java.util.stream.Collectors;

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
    public List<OrdenDTO> obtenerOrdenPorUsr(@PathVariable String login ) {
        return ordenService.obtenerPorClientUsr(login);
    }

    @GetMapping("/{id}")
    public List<OrdenDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return Collections.singletonList(ordenService.obtenerPorId(id));
    }


    @GetMapping("/todas")
    public ResponseEntity<List<TodasLasOrdenesDTO>> obtenerTodasLasOrdenes() {
        List<TodasLasOrdenesDTO> r = ordenService.obtenerUsuariosYOrdenes();
        return ResponseEntity.ok(r);
    }


    @Transactional
    @DeleteMapping("/{userId}/{orderId}/detalles/{productoId}")
    public ResponseEntity<String> eliminarProductoDeOrdenDetalles(@PathVariable Long userId,
                                                                  @PathVariable Long orderId,
                                                                  @PathVariable Long productoId) {
        try {
            ordenDetalleService.eliminarProductoDeOrdenDetalles(userId, orderId, productoId);
            return ResponseEntity.ok("Producto eliminado de la orden exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("/{usr}/productos")
    public ResponseEntity<OrdenDetalles> agregarProductoAOrdenDetalles(
            @PathVariable String usr,
            @RequestParam Long idProducto,
            @RequestParam int cantidad) {
        OrdenDetalles ordenDetalles = ordenDetalleService.agregarProductoAOrdenDetalles(usr, idProducto, cantidad);
        return ResponseEntity.ok(ordenDetalles);
    }

    @PostMapping("/{idCliente}/finalizar")
    public ResponseEntity<OrdenAndDetailDTO> finalizarOrden(
            @PathVariable Long idCliente,
            @RequestBody List<OrdenDetalles> detallesOrden,
            @RequestParam int idMetodoPago,
            @RequestParam int idOrdenEstado) {
        Orden orden = ordenService.crearOrden(idCliente, detallesOrden, idMetodoPago, idOrdenEstado);

        OrdenAndDetailDTO r = new OrdenAndDetailDTO(
                orden.getIdOrden(),
                orden.getOrdenMonto(),
                orden.getOrdenEstado().getOrdenEstadoNombre(),
                orden.getMetodoPago().getMetodo_pago(),
                orden.getOrdenDate(),
                orden.getDateDelivery(),
                orden.getOrdenDetalles().stream()
                        .map(d ->new DetallesDTO(
                                d.getProducto().getIdProducto(),
                                d.getProducto().getProductName(),
                                d.getCantidadProducto(),
                                d.getSubTotalPrecio()
                        ))
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(r);
    }



}
