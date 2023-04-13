package com.tutorial.crud.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tutorial.crud.swagger.entity.Telefono;

import java.io.IOException;

public class TelefonoSerializer extends JsonSerializer<Telefono> {


    @Override
    public void serialize(Telefono telefono, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        // agregar aquí la lógica para serializar la clase Telefono
        jsonGenerator.writeEndObject();
    }
}