package org.backend.trabajo.backendproyecto.RuntimeExceptionCustom;

public class ClienteAlreadyExistsException extends RuntimeException {
    public ClienteAlreadyExistsException(String message) {
        super(message);
    }
}
