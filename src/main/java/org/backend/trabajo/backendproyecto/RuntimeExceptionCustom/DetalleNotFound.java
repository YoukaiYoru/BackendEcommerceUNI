package org.backend.trabajo.backendproyecto.RuntimeExceptionCustom;


public class DetalleNotFound extends RuntimeException{
    public DetalleNotFound(String message){
        super(message);
    }
}
