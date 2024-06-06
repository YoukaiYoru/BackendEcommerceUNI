package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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


    @GetMapping("/orden/user/{id}")
    public List<OrdenDTO> obtenerOrdenPorUsr(@PathVariable String usr) {
        return ordenService.obtenerPorClientUsr((usr));
    }

    @GetMapping("/orden/{id}")
    public List<OrdenDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return Collections.singletonList(ordenService.obtenerPorId(id));
    }
}
