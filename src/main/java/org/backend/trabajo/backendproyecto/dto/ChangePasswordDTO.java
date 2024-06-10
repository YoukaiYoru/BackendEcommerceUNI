package org.backend.trabajo.backendproyecto.dto;

public record ChangePasswordDTO(
        String login,
        String oldPassword,
        String newPassword
) {
}
