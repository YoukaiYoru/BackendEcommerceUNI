package org.backend.trabajo.backendproyecto.RuntimeExceptionCustom;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String credencialesInválidas) {
        super (credencialesInválidas);
    }
}
