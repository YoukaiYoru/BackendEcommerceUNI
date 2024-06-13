package org.backend.trabajo.backendproyecto.dto.OdenDTO;

import java.time.LocalDate;
import java.util.List;

public record OrdenAndDetailDTO(
        Long idOrden,
        Float ordenAmount,
        String ordenEstado,
        String metodoPago,
        LocalDate fechaOrden,
        LocalDate fechaDelivery,
        List<DetallesDTO> detallesDTOList
) {
}
