/*
 * Copyright (c) 2020-2025 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.airbyte.protocol.models.v0.AirbyteMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class AirbyteProtocolSchemaTest {

  @Test
  void testFile() throws IOException {
    final String schema = Files.readString(AirbyteProtocolSchema.PROTOCOL.getFile().toPath(), StandardCharsets.UTF_8);
    assertTrue(schema.contains("title"));
  }

  @Test
  void testPrepareKnownSchemas() {
    for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
      assertTrue(Files.exists(value.getFile().toPath()));
    }
  }

  @Test
  void testJsonSchemaType() {
    for (final AirbyteProtocolSchema value : AirbyteProtocolSchema.values()) {
      assertTrue(Files.exists(value.getFile().toPath()));
    }
  }

  @Test
  void testVersionedObjectsAccessibility() {
    final AirbyteMessage message = new AirbyteMessage()
        .withType(AirbyteMessage.Type.SPEC);
    final AirbyteMessage messageV0 = new AirbyteMessage()
        .withType(AirbyteMessage.Type.SPEC);

    // This only works as long as the default version and v0 are equal
    final AirbyteMessage deserializedMessage =
        Jsons.deserialize(Jsons.serialize(message), AirbyteMessage.class);
    assertEquals(messageV0, deserializedMessage);
  }

}
