package org.backend.trabajo.backendproyecto.dto;

public record ClienteDTO(
        Long idClient,
        String clientUsr,
        String client_password,
        String client_firstName,
        String client_lastName,
        String client_email,
        String client_phone) {
}
