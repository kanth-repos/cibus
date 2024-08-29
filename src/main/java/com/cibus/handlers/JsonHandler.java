package com.cibus.handlers;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.opensymphony.xwork2.ActionInvocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.cibus.serialize.Violation;

import java.io.IOException;
import java.io.Writer;

import javax.validation.ConstraintViolation;

import org.apache.struts2.rest.handler.JacksonJsonHandler;

public class JsonHandler extends JacksonJsonHandler {
  public String fromObject(
    ActionInvocation invocation, Object obj, String resultCode, Writer stream
  ) throws IOException {
    mapper.writeValue(stream, obj);
    return null;
  }

  private ObjectMapper mapper = new ObjectMapper();

  public JsonHandler() {
    SimpleModule module = new SimpleModule();
    module.addSerializer(ConstraintViolation.class, new Violation());
    mapper.registerModule(module);
  }
}
