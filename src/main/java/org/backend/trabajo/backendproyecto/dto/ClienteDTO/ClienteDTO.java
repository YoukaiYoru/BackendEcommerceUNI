package org.backend.trabajo.backendproyecto.dto.ClienteDTO;

public record ClienteDTO(
        Long idClient,
        String clientUser,
        String clientPassword,
        String clientFirstName,
        String clientLastName,
        String clientEmail,
        String clientPhone) {
}
