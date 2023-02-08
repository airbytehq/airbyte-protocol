/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models;

/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Collections;

@SuppressWarnings({"PMD.AvoidReassigningParameters", "PMD.AvoidCatchingThrowable"})
public class Jsons {

  // Object Mapper is thread-safe
  private static final ObjectMapper OBJECT_MAPPER;

  static {
    final var result = new ObjectMapper().registerModule(new JavaTimeModule());
    result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    result.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
    OBJECT_MAPPER = result;
  }

  public static <T> String serialize(final T object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (final JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static JsonNode deserialize(final String jsonString) {
    try {
      return OBJECT_MAPPER.readTree(jsonString);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T deserialize(final String jsonString, final Class<T> klass) {
    try {
      return OBJECT_MAPPER.readValue(jsonString, klass);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> JsonNode jsonNode(final T object) {
    return OBJECT_MAPPER.valueToTree(object);
  }

  public static JsonNode emptyObject() {
    return jsonNode(Collections.emptyMap());
  }

  public static <T> T object(final JsonNode jsonNode, final Class<T> klass) {
    return OBJECT_MAPPER.convertValue(jsonNode, klass);
  }

  public static <T> T object(final JsonNode jsonNode, final TypeReference<T> typeReference) {
    return OBJECT_MAPPER.convertValue(jsonNode, typeReference);
  }

}
