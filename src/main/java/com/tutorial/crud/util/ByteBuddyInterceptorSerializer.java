package com.tutorial.crud.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

import java.io.IOException;

public class ByteBuddyInterceptorSerializer extends JsonSerializer<ByteBuddyInterceptor> {

    @Override
    public void serialize(ByteBuddyInterceptor value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("class", value.getClass().getName());
        gen.writeEndObject();
    }
}
