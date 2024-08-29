package com.cibus.serialize;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import javax.validation.ConstraintViolation;

public class Violation extends JsonSerializer<ConstraintViolation<?>> {
  @Override
  public void serialize(
    ConstraintViolation<?> value, 
    JsonGenerator gen, 
    SerializerProvider serializers
  ) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("property", value.getPropertyPath().toString());
    gen.writeStringField("invalidValue", value.getInvalidValue() == null ? "null" : value.getInvalidValue().toString());
    gen.writeStringField("message", value.getMessage());
    gen.writeEndObject();
  }
}
