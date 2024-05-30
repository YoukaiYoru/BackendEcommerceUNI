package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return convierteDatos(productoRepository.findAll());
    }


    public List<ProductoDTO> convierteDatos(List<Producto> productos){
        return productos.stream()
                .map(p -> new ProductoDTO(p.getIdProducto(),p.getProd_nombre(),p.getProd_precio(),p.getProd_descripcion(),p.getProd_imagen()))
                .collect(Collectors.toList());
    }


    public ProductoDTO obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()){
            Producto p = producto.get();
            return new ProductoDTO(p.getIdProducto(),p.getProd_nombre(),p.getProd_precio(),p.getProd_descripcion(),p.getProd_imagen());
        }
        return null;
    }
}
