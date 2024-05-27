package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping()
    public List<OrdenDTO> obtenerProductos() {
        return ordenService.obtenerTodasLasOrdenes();
    }


}
