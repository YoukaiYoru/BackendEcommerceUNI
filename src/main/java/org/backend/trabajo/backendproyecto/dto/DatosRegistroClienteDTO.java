package org.backend.trabajo.backendproyecto.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroClienteDTO(
        @NotBlank
        String clientUser,
        @NotBlank
        String clientPassword,
        @NotBlank
        String clientFirstName,
        @NotBlank
        String clientLastName,
        @NotBlank
        @Email
        String clientEmail,
        @NotBlank
        String clientPhone
) {
}