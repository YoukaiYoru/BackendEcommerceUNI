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

    public List<ProductoDTO> convierteDatos(List<Producto> productoList){
        return productoList.stream()
                .map(p -> new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl())).
                collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Long id_product) {
        Optional<Producto> producto = productoRepository.findById(id_product);
        if (producto.isPresent()){
            Producto p = producto.get();
            return new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl());
        }
        return null;
    }
}
