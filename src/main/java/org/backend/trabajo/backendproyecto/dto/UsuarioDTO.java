package org.backend.trabajo.backendproyecto.dto;

public record UsuarioDTO(
        Long idUsuario,
        String usrLogin,
        String usrNombre,
        String usrApellido,
        String usrCorreo,
        String usrTelefono
) {
}
