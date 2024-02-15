package com.nttdata.customer.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class CustomerSerializer extends JsonSerializer<Customer> {
    @Override
    public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", customer.getId());
        jsonGenerator.writeStringField("name", customer.getName());
        jsonGenerator.writeStringField("tipo", customer.getTipo().toString()); // Assuming tipo is an enum
        jsonGenerator.writeEndObject();
    }
}