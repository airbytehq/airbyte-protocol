/*
 * Copyright (c) 2020-2025 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models;

import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.BINARY_DATA_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.BOOLEAN_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.DATE_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.INTEGER_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.NUMBER_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.STRING_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.TIMESTAMP_WITHOUT_TIMEZONE_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.TIMESTAMP_WITH_TIMEZONE_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.TIME_WITHOUT_TIMEZONE_REFERENCE;
import static io.airbyte.protocol.models.JsonSchemaReferenceTypes.TIME_WITH_TIMEZONE_REFERENCE;

import java.util.Map;
import java.util.Set;

public class JsonSchemaPrimitiveUtil {

  public enum JsonSchemaPrimitive {
    // V0 schema primitives
    STRING,
    NUMBER,
    OBJECT,
    ARRAY,
    BOOLEAN,
    JSONB,
    NULL,
    // V1 schema primitives
    STRING_V1,
    BINARY_DATA_V1,
    DATE_V1,
    TIMESTAMP_WITH_TIMEZONE_V1,
    TIMESTAMP_WITHOUT_TIMEZONE_V1,
    TIME_WITH_TIMEZONE_V1,
    TIME_WITHOUT_TIMEZONE_V1,
    NUMBER_V1,
    INTEGER_V1,
    BOOLEAN_V1;
  }

  public static final Set<JsonSchemaPrimitive> VO_JSON_SCHEMA_PRIMITIVE_SET =
      Set.of(
          JsonSchemaPrimitive.STRING,
          JsonSchemaPrimitive.NUMBER,
          JsonSchemaPrimitive.OBJECT,
          JsonSchemaPrimitive.ARRAY,
          JsonSchemaPrimitive.BOOLEAN,
          JsonSchemaPrimitive.JSONB,
          JsonSchemaPrimitive.NULL);

  public static final boolean isV0Schema(final JsonSchemaPrimitive type) {
    return VO_JSON_SCHEMA_PRIMITIVE_SET.contains(type);
  }

  public static final Map<JsonSchemaPrimitive, String> PRIMITIVE_TO_REFERENCE_BIMAP =
      Map.of(
          JsonSchemaPrimitive.STRING_V1, STRING_REFERENCE,
          JsonSchemaPrimitive.BINARY_DATA_V1, BINARY_DATA_REFERENCE,
          JsonSchemaPrimitive.DATE_V1, DATE_REFERENCE,
          JsonSchemaPrimitive.TIMESTAMP_WITH_TIMEZONE_V1, TIMESTAMP_WITH_TIMEZONE_REFERENCE,
          JsonSchemaPrimitive.TIMESTAMP_WITHOUT_TIMEZONE_V1, TIMESTAMP_WITHOUT_TIMEZONE_REFERENCE,
          JsonSchemaPrimitive.TIME_WITH_TIMEZONE_V1, TIME_WITH_TIMEZONE_REFERENCE,
          JsonSchemaPrimitive.TIME_WITHOUT_TIMEZONE_V1, TIME_WITHOUT_TIMEZONE_REFERENCE,
          JsonSchemaPrimitive.NUMBER_V1, NUMBER_REFERENCE,
          JsonSchemaPrimitive.INTEGER_V1, INTEGER_REFERENCE,
          JsonSchemaPrimitive.BOOLEAN_V1, BOOLEAN_REFERENCE);

}
