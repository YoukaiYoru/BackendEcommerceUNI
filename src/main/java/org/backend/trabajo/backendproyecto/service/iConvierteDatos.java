package org.backend.trabajo.backendproyecto.service;

public interface iConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
