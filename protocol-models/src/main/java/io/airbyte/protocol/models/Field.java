/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models;

import io.airbyte.protocol.protos.AirbyteCatalog;
import io.airbyte.protocol.protos.AirbyteMessage;
import io.airbyte.protocol.protos.AirbyteStreamState;
import io.airbyte.protocol.protos.StreamDescriptor;

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

  public static void main(String[] args) {
    System.out.println("poop");

    System.out.println(AirbyteStreamState.getDefaultInstance().isInitialized());
    System.out.println(AirbyteStreamState.newBuilder().setStreamDescriptor(StreamDescriptor.newBuilder().setName("Test").build()));
    System.out.println(AirbyteMessage.newBuilder().build());
  }
}
