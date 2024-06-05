package org.backend.trabajo.backendproyecto.dto;

public record ClienteDTO(
        int id_client,
        String client_user,
        String client_password,
        String client_firstName,
        String client_lastName,
        String client_email,
        String client_phone) {
}
