/*
 * Copyright (c) 2020-2025 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models.v0;

import io.airbyte.protocol.models.JsonSchemaType;
import java.util.List;

public class Field extends CommonField<JsonSchemaType> {

  private List<Field> subFields;

  public Field(final String name, final JsonSchemaType type) {
    super(name, type);
  }

  public Field(final String name, final JsonSchemaType type, List<Field> subFields) {
    super(name, type);
    this.subFields = subFields;
  }

  public static Field of(final String name, final JsonSchemaType type) {
    return new Field(name, type);
  }

  public static Field of(final String name, final JsonSchemaType type, List<Field> properties) {
    return new Field(name, type, properties);
  }

  public List<Field> getSubFields() {
    return subFields;
  }

}
