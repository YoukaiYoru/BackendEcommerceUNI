package org.backend.trabajo.backendproyecto.RuntimeExceptionCustom;

public class DetalleNotFoundException extends RuntimeException {
    public DetalleNotFoundException(String productoNoEncontrado) {
        super(productoNoEncontrado);
    }
}
