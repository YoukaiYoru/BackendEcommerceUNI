package org.backend.trabajo.backendproyecto.dto;

public record ClienteDTO(
        Long idClient,
        String clientUser,
        String clientPassword,
        String clientFirstName,
        String clientLastName,
        String clientEmail,
        String clientPhone) {
}
