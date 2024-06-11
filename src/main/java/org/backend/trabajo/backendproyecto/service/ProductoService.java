package org.backend.trabajo.backendproyecto.service;

import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.dto.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Categoria;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.CategoriaRepository;
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
    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<ProductoDTO> obtenerTodosLosProductos() {
        return convierteDatos(productoRepository.findAll());
    }

    public List<ProductoDTO> convierteDatos(List<Producto> productoList){
        return productoList.stream()
                .map(p -> new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl(),p.getCategoria().getCategoriaDescripcion())).
                collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Long id_product) {
        Optional<Producto> producto = productoRepository.findById(id_product);
        if (producto.isPresent()){
            Producto p = producto.get();
            return new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl(),p.getCategoria().getCategoriaDescripcion());
        }
        return null;
    }

    public List<ProductoDTO> obtenerProductosPorCategoria(String categoriaDescripcion) {
        Optional<Categoria> categoria = categoriaRepository.findCategoriaByCategoriaDescripcion(categoriaDescripcion);
        if (categoria.isPresent()) {
            List<Producto> productos = productoRepository.findByCategoria(categoria.get());
            return convierteDatos(productos);
        }
        return List.of(); // Devuelve una lista vacía si no se encuentra la categoría
    }

    //Agrega producto y suma un contador a la categoria
    @Transactional
    public void agregarProducto(DatosProductoDTO datosProductoDTO) {
        Optional<Categoria> categoria = categoriaRepository.findCategoriaByCategoriaDescripcion(datosProductoDTO.categoria_producto());
        if (categoria.isPresent()){
            Producto producto = new Producto(datosProductoDTO);
            producto.setCategoria(categoria.get());
            productoRepository.save(producto);

            Categoria c = categoria.get();
            c.setCategoriaContador(c.getCategoriaContador() + 1);
            categoriaRepository.save(c);
        }

    }





}
