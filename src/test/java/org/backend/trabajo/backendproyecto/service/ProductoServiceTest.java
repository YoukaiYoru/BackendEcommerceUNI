import org.backend.trabajo.backendproyecto.dto.ProductoDTO.ProductoDTO;
import org.backend.trabajo.backendproyecto.dto.ProductoDTO.DatosProductoDTO;
import org.backend.trabajo.backendproyecto.model.Categoria;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.CategoriaRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.backend.trabajo.backendproyecto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testObtenerTodosLosProductos() {
        // Given
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(), new Categoria()));
        productos.add(new Producto(), new Categoria()))


        Mockito.when(productoRepository.findAll()).thenReturn(productos);

        // When
        List<ProductoDTO> resultado = productoService.obtenerTodosLosProductos();

        // Then
        assertEquals(productos.size(), resultado.size());
        // Agrega más aserciones según sea necesario
    }

    @Test
    public void testObtenerPorIdExistente() {
        // Given
        Long idProducto = 1L;
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setProductName("Producto de prueba");
        producto.setProductDescription("Descripción del producto de prueba");
        producto.setProductPrice(20.0f);
        producto.setProductStock(30);
        producto.setProductImgUrl("imagen_prueba.jpg");
        Categoria categoria = new Categoria();
        categoria.setCategoriaDescripcion("Categoria de prueba");
        producto.setCategoria(categoria);

        Mockito.when(productoRepository.findById(idProducto)).thenReturn(Optional.of(producto));


        // When
        ProductoDTO resultado = productoService.obtenerPorId(idProducto);

        // Then
        assertNotNull(resultado);
        assertEquals(producto.getIdProducto(), resultado.id_product());
        // Agrega más aserciones según sea necesario
    }

    @Test
    public void testObtenerPorIdNoExistente() {
        // Given
        Long idProducto = 1L;
        Mockito.when(productoRepository.findById(idProducto)).thenReturn(Optional.empty());

        // When
        ProductoDTO resultado = productoService.obtenerPorId(idProducto);

        // Then
        assertNull(resultado);
    }

}
