package org.backend.trabajo.backendproyecto.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaClienteDTO(
        @NotBlank String clientUser,
        @NotBlank String clientPassword,
        @NotBlank String clientFirstName,
        @NotBlank String clientLastName,
        @NotBlank @Email String clientEmail,
        @NotBlank String clientPhone
) {
        public String getClientUser() {
                return clientUser;
        }
        public String getClientPassword() {
                return clientPassword;
        }
        public String getClientFirstName() {
                return clientFirstName;
        }
        public String getClientLastName() {
                return clientLastName;
        }
        public String getClientEmail() {
                return clientEmail;
        }
        public String getClientPhone() {
                return clientPhone;
        }
}