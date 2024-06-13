package org.backend.trabajo.backendproyecto.dto.ClienteDTO;

public record ChangePasswordDTO(
        String login,
        String oldPassword,
        String newPassword
) {
}
