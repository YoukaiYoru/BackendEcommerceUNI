package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.dto.ProductoDTO.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Categoria;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.CategoriaRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Obtener todos los productos
    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return convierteDatos(productos);
    }
    //Convertir datos a JSON
    public List<ProductoDTO> convierteDatos(List<Producto> productoList){
        return productoList.stream()
                .map(p -> new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl(),p.getCategoria().getCategoriaDescripcion())).
                collect(Collectors.toList());
    }

    //Obtener productos por ID
    public ProductoDTO obtenerPorId(Long id_product) {
        Optional<Producto> producto = productoRepository.findById(id_product);
        if (producto.isPresent()){
            Producto p = producto.get();
            return new ProductoDTO(p.getIdProducto(),p.getProductName(),p.getProductDescription(),p.getProductPrice(),p.getProductStock(),p.getProductImgUrl(),p.getCategoria().getCategoriaDescripcion());
        }
        return null;
    }

    //Obtener productos por categoria (Filtro de producto por categoria)
    public List<ProductoDTO> obtenerProductosPorCategoria(String categoriaTipo) {
        Optional<Categoria> categoria = categoriaRepository.findCategoriaByCategoriaTipo(categoriaTipo);
        if (categoria.isPresent()) {
            List<Producto> productos = productoRepository.findByCategoria(categoria.get());
            return convierteDatos(productos);
        }
        return List.of();
    }

    //Eliminar producto por ID
    public void eliminarPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        producto.ifPresent(p -> productoRepository.delete(p));
    }

    //Modificar el precio de un producto por ID
    public Producto actualizarPrecioPorId(Long id, Float precio) {
        // Buscar el producto por su ID
        Optional<Producto> optionalProducto = productoRepository.findById(id);

        if (optionalProducto.isPresent()) {
            // Si el producto existe, actualizar el precio y guardar los cambios
            Producto productoActualizado = optionalProducto.get();
            productoActualizado.setProductPrice(precio);
            return productoRepository.save(productoActualizado);
        } else {
            // Si el producto no se encuentra, lanzar una excepción
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    //Servicio de agregar un producto, modificar contador de categorias -> Valida el ingreso de imagenes para el producto
    public Producto agregarProducto(String nombre, float price, String descripcion, int cantidad, String categoriaNombre, MultipartFile imagen) throws IOException {
        String rutaImagen = guardarImagen(imagen);
        Categoria categoria = categoriaRepository.findByCategoriaTipo(categoriaNombre)
                .orElseGet(() -> {
                    Categoria nuevaCategoria = new Categoria();
                    nuevaCategoria.setCategoriaTipo(categoriaNombre);
                    nuevaCategoria.setCategoriaContador(0);
                    return categoriaRepository.save(nuevaCategoria);
                });

        Producto productoExistente = productoRepository.findByProductName(nombre).orElse(null);

        if (productoExistente != null) {
            productoExistente.setProductStock(productoExistente.getProductStock() + cantidad);
            productoExistente.setProductImgUrl(rutaImagen);
            productoExistente.setProductDescription(descripcion);
            productoExistente.setCategoria(categoria);
            productoExistente.setProductPrice(price);
            return productoRepository.save(productoExistente);
        } else {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setProductName(nombre);
            nuevoProducto.setProductDescription(descripcion);
            nuevoProducto.setProductStock(cantidad);
            nuevoProducto.setProductImgUrl(rutaImagen);
            nuevoProducto.setCategoria(categoria);
            nuevoProducto.setProductPrice(price);
            categoria.setCategoriaContador(categoria.getCategoriaContador() + 1);
            categoriaRepository.save(categoria);
            return productoRepository.save(nuevoProducto);
        }
    }

    //Guardar imagen en la direccion seleccionada
    private String guardarImagen(MultipartFile file) throws IOException {
        String nombreArchivo = file.getOriginalFilename();
        Path rutaArchivo = Paths.get("src/main/resources/static/img/" + nombreArchivo);
        Files.copy(file.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        return rutaArchivo.toString();
    }

    /*//Agregar tipo de producto y aumentando 1 a la categoria
    @Transactional
    public Long agregarProducto(DatosProductoDTO datosProductoDTO) {
        Optional<Categoria> categoria = categoriaRepository.findCategoriaByCategoriaTipo(datosProductoDTO.categoria_producto());
        if (categoria.isPresent()){
            Producto producto = new Producto(datosProductoDTO);
            producto.setCategoria(categoria.get());
            Producto productoGuardado = productoRepository.save(producto);

            Categoria c = categoria.get();
            c.setCategoriaContador(c.getCategoriaContador() + 1);
            categoriaRepository.save(c);
            return productoGuardado.getIdProducto();
        }
        return null;
    }
     */
}
