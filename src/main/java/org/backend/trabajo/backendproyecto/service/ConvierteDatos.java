package org.backend.trabajo.backendproyecto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class ConvierteDatos implements iConvierteDatos{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json,Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertirAJson(Object objeto) {
        try {
            return objectMapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}