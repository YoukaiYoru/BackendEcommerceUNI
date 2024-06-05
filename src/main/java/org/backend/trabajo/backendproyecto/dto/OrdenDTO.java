package org.backend.trabajo.backendproyecto.dto;

import org.backend.trabajo.backendproyecto.model.MetodoPago;
import org.backend.trabajo.backendproyecto.model.OrdenEstado;
import org.backend.trabajo.backendproyecto.model.Cliente;

import java.time.LocalDate;

public record OrdenDTO(
        int id_order,
        float order_amount,
        LocalDate order_date,
        LocalDate date_delivery,
        Cliente id_client,
        OrdenEstado id_order_status,
        MetodoPago id_payment_method) {
}
