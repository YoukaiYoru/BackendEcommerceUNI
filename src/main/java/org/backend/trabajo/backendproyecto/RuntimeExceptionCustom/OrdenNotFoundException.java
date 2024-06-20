package org.backend.trabajo.backendproyecto.RuntimeExceptionCustom;

public class OrdenNotFoundException extends RuntimeException {
    public OrdenNotFoundException(String ordenNoEncontrada) {
        super(ordenNoEncontrada);
    }
}
