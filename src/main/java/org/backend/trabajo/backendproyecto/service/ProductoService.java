package org.backend.trabajo.backendproyecto.service;

import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.ProductoDTO;
import org.backend.trabajo.backendproyecto.model.Categoria;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.CategoriaRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public List<ProductoDTO> obtenerProductosPorCategoria(String categoriaTipo) {
        Optional<Categoria> categoria = categoriaRepository.findCategoriaByCategoriaTipo(categoriaTipo);
        if (categoria.isPresent()) {
            List<Producto> productos = productoRepository.findByCategoria(categoria.get());
            return convierteDatos(productos);
        }
        return List.of();
    }

    //Agrega producto y suma un contador a la categoria
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

    public void eliminarPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        producto.ifPresent(p -> productoRepository.delete(p));
    }

    public Producto actualizarPrecioPorId(Long id,Float precio) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            Producto productoActualizado = producto.get();
            productoActualizado.setProductPrice(precio);
            return productoRepository.save(productoActualizado);
        }
        else{
            throw new RuntimeException("Producto no encontrado");
        }
    }

    /*GuardarImagenDelUsuario
    private static final String DIRECTORIO_IMAGENES = "src//main//resources//static//img"; // Assuming resources folder is on classpath

    public String guardarImagenxd(MultipartFile file){
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Validate filename to prevent potential security vulnerabilities
        if (nombreArchivo.contains("..")) {
            throw new IllegalArgumentException("Filename contains invalid characters");
        }
        File directorio = new File(DIRECTORIO_IMAGENES);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        if(!file.isEmpty()) {
            Path path = Paths.get(DIRECTORIO_IMAGENES);
            String rutaAbsoluta =path.toFile().getAbsolutePath();
            try {
                byte[] bytes = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta, nombreArchivo);
                Files.write(rutaCompleta, bytes);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return DIRECTORIO_IMAGENES + "/" + nombreArchivo;
    }
    */

    /*public Producto guardarProductoConImagen(String nombreArchivo, MultipartFile file) throws IOException {
        String rutaSrcImagen = guardarImagen(file);
        Producto producto = new Producto();
        producto.setProductImgUrl(rutaSrcImagen);
        return productoRepository.save(producto);
    }

    private String guardarImagen(MultipartFile imagen) throws IOException {
        String nombreArchivo = imagen.getOriginalFilename();
        Path rutaArchivo = Paths.get("src/main/resources/static/img/" + nombreArchivo);
        Files.copy(imagen.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        return rutaArchivo.toString();
    }

     */
    //modificar imagen de un servicio
    public Producto guardarProductoConImagen(String nombre, String descripcion, int cantidad, String categoriaNombre, MultipartFile imagen) throws IOException {
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
            return productoRepository.save(productoExistente);
        } else {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setProductName(nombre);
            nuevoProducto.setProductDescription(descripcion);
            nuevoProducto.setProductStock(cantidad);
            nuevoProducto.setProductImgUrl(rutaImagen);
            nuevoProducto.setCategoria(categoria);
            categoria.setCategoriaContador(categoria.getCategoriaContador() + 1);
            categoriaRepository.save(categoria);
            return productoRepository.save(nuevoProducto);
        }
    }

    private String guardarImagen(MultipartFile file) throws IOException {
        String nombreArchivo = file.getOriginalFilename();
        Path rutaArchivo = Paths.get("src/main/resources/static/img/" + nombreArchivo);
        Files.copy(file.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        return rutaArchivo.toString();
    }
}
