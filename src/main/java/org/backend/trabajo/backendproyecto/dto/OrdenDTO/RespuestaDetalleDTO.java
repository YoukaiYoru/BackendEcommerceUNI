package org.backend.trabajo.backendproyecto.dto.OrdenDTO;

import java.time.LocalDate;
import java.util.List;

public record RespuestaDetalleDTO(
        Long idOrden,
        Float ordenMonto,
        LocalDate ordenDate,
        List<DetallesDTO> Detalles

) {
}
