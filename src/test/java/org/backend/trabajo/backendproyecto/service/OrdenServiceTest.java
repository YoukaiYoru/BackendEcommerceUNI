import org.backend.trabajo.backendproyecto.dto.OrdenDTO.OrdenDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.model.*;
import org.backend.trabajo.backendproyecto.repository.*;
import org.backend.trabajo.backendproyecto.service.OrdenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import static org.mockito.Mockito.*;

class OrdenServiceTest {

    @InjectMocks
    private OrdenService ordenService;

    @Mock
    private OrdenRepository ordenRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private OrdenDetalleRepository ordenDetalleRepository;

    @Mock
    private MetodoPagoRepository metodoPagoRepository;

    @Mock
    private OrdenEstadoRepository ordenEstadoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasLasOrdenes() {
        // Arrange
        List<Orden> ordenes = Arrays.asList(
                new Orden(/* Datos de prueba para la primera orden */),
                new Orden(/* Datos de prueba para la segunda orden */)
        );
        when(ordenRepository.findAll()).thenReturn(ordenes);

        // Act
        List<OrdenDTO> resultado = ordenService.obtenerTodasLasOrdenes();

        // Assert
        // Asegúrate de verificar que resultado coincida con los datos de prueba
    }

    @Test
    void testObtenerUsuariosYOrdenes() {
        // Arrange
        List<Orden> ordenes = Arrays.asList(
                new Orden(/* Datos de prueba para la primera orden */),
                new Orden(/* Datos de prueba para la segunda orden */)
        );
        when(ordenRepository.findAll()).thenReturn(ordenes);

        // Act
        List<TodasLasOrdenesDTO> resultado = ordenService.obtenerUsuariosYOrdenes();

        // Assert
        // Asegúrate de verificar que resultado coincida con los datos de prueba
    }

    // Test para el método obtenerPorId

    @Test
    void testObtenerPorId_Existente() {
        // Arrange
        Long idOrdenExistente = 1L;
        Orden ordenExistente = new Orden(/* Datos de prueba para la orden existente con id 1 */);
        when(ordenRepository.findById(idOrdenExistente)).thenReturn(Optional.of(ordenExistente));

        // Act
        OrdenDTO resultado = ordenService.obtenerPorId(idOrdenExistente);

        // Assert
        // Asegúrate de verificar que resultado coincida con los datos de prueba
    }

    @Test
    void testObtenerPorId_NoExistente() {
        // Arrange
        Long idOrdenNoExistente = 100L;
        when(ordenRepository.findById(idOrdenNoExistente)).thenReturn(Optional.empty());

        // Act
        OrdenDTO resultado = ordenService.obtenerPorId(idOrdenNoExistente);

        // Assert
        // Asegúrate de que resultado sea nulo, ya que no hay orden con el id proporcionado
    }

    // Test para el método obtenerPorClientUsr

    @Test
    void testObtenerPorClientUsr_Existente() {
        // Arrange
        String usuarioExistente = "usuario1";
        List<Orden> ordenesUsuario = Arrays.asList(
                new Orden(/* Datos de prueba para la primera orden del usuario1 */),
                new Orden(/* Datos de prueba para la segunda orden del usuario1 */)
        );
        when(ordenRepository.findByClientUser(usuarioExistente)).thenReturn(ordenesUsuario);

        // Act
        List<OrdenDTO> resultado = ordenService.obtenerPorClientUsr(usuarioExistente);

        // Assert
        // Asegúrate de verificar que resultado coincida con los datos de prueba
    }

    @Test
    void testObtenerPorClientUsr_NoExistente() {
        // Arrange
        String usuarioNoExistente = "usuario2";
        when(ordenRepository.findByClientUser(usuarioNoExistente)).thenReturn(Collections.emptyList());

        // Act
        List<OrdenDTO> resultado = ordenService.obtenerPorClientUsr(usuarioNoExistente);

        // Assert
        // Asegúrate de que resultado sea una lista vacía, ya que no hay órdenes para el usuario proporcionado
    }

    // Test para el método crearOrden

    @Test
    void testCrearOrden() {
        // Arrange
        Long idCliente = 1L;
        List<OrdenDetalles> detallesOrden = Arrays.asList(
                new OrdenDetalles(/* Datos de prueba para el primer detalle de la orden */),
                new OrdenDetalles(/* Datos de prueba para el segundo detalle de la orden */)
        );
        int idMetodoPago = 1;
        int idOrdenEstado = 1;
        when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(new Cliente(/* Datos de prueba para el cliente */datosRegistroClienteDTO)));
        when(metodoPagoRepository.findById(idMetodoPago)).thenReturn(Optional.of(new MetodoPago(/* Datos de prueba para el método de pago */)));
        when(ordenEstadoRepository.findById(idOrdenEstado)).thenReturn(Optional.of(new OrdenEstado(/* Datos de prueba para el estado de la orden */)));
        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(new Producto(/* Datos de prueba para el producto */)));

        // Act
        Orden ordenCreada = ordenService.crearOrden(idCliente, detallesOrden, idMetodoPago, idOrdenEstado);

        // Assert
        // Asegúrate de verificar que la orden se haya creado correctamente y los datos sean los esperados
    }
}