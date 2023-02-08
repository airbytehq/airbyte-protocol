/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.protocol.models;

/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;

// todo (cgardens) - we need the ability to identify jsonschemas that Airbyte considers invalid for
// a connector (e.g. "not" keyword).
@Slf4j
@SuppressWarnings("PMD.SwitchStmtsShouldHaveDefault")
public class JsonSchemas {

  private static final String JSON_SCHEMA_ENUM_KEY = "enum";
  private static final String JSON_SCHEMA_TYPE_KEY = "type";
  private static final String JSON_SCHEMA_PROPERTIES_KEY = "properties";
  private static final String JSON_SCHEMA_ITEMS_KEY = "items";

  // all JSONSchema types.
  private static final String ARRAY_TYPE = "array";
  private static final String OBJECT_TYPE = "object";
  private static final String STRING_TYPE = "string";
  private static final String ONE_OF_TYPE = "oneOf";
  private static final String ALL_OF_TYPE = "allOf";
  private static final String ANY_OF_TYPE = "anyOf";

  private static final Set<String> COMPOSITE_KEYWORDS = Set.of(ONE_OF_TYPE, ALL_OF_TYPE,
      ANY_OF_TYPE);

  /*
   * JsonReferenceProcessor relies on all the json in consumes being in a file system (not in a jar).
   * This method copies all the json configs out of the jar into a temporary directory so that
   * JsonReferenceProcessor can find them.
   */
  public static <T> Path prepareSchemas(final String resourceDir, final Class<T> klass) {
    try {
      final List<String> filenames;
      try (final Stream<Path> resources = listResources(klass, resourceDir)) {
        filenames = resources.map(p -> p.getFileName().toString())
            .filter(p -> p.endsWith(".yaml"))
            .collect(Collectors.toList());
      }

      final Path configRoot = Files.createTempDirectory("schemas");
      for (final String filename : filenames) {
        final var resource = Resources.getResource(String.format("%s/%s", resourceDir, filename));
        final var resourceContents = Resources.toString(resource, StandardCharsets.UTF_8);

        Files.writeString(configRoot.resolve(filename), resourceContents, StandardCharsets.UTF_8);
      }
      return configRoot;
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Traverse a JsonSchema object. The provided consumer will be called at each node with the node and
   * the path to the node.
   *
   * @param jsonSchema - JsonSchema object to traverse
   * @param consumer - accepts the current node and the path to that node.
   */
  public static void traverseJsonSchema(final JsonNode jsonSchema, final BiConsumer<JsonNode, List<FieldNameOrList>> consumer) {
    traverseJsonSchemaInternal(jsonSchema, new ArrayList<>(), consumer);
  }

  /**
   * Traverse a JsonSchema object. At each node, map a value.
   *
   * @param jsonSchema - JsonSchema object to traverse
   * @param mapper - accepts the current node and the path to that node. whatever is returned will be
   *        collected and returned by the final collection.
   * @param <T> - type of objects being collected
   * @return - collection of all items that were collected during the traversal. Returns a { @link
   *         Collection } because there is no order or uniqueness guarantee so neither List nor Set
   *         make sense.
   */
  public static <T> List<T> traverseJsonSchemaWithCollector(final JsonNode jsonSchema, final BiFunction<JsonNode, List<FieldNameOrList>, T> mapper) {
    // for the sake of code reuse, use the filtered collector method but makes sure the filter always
    // returns true.
    return traverseJsonSchemaWithFilteredCollector(jsonSchema,
        (node, path) -> Optional.ofNullable(mapper.apply(node, path)));
  }

  /**
   * Traverse a JsonSchema object. At each node, optionally map a value.
   *
   * @param jsonSchema - JsonSchema object to traverse
   * @param mapper - accepts the current node and the path to that node. if it returns an empty
   *        optional, nothing will be collected, otherwise, whatever is returned will be collected and
   *        returned by the final collection.
   * @param <T> - type of objects being collected
   * @return - collection of all items that were collected during the traversal. Returns values in
   *         preoorder traversal order.
   */
  public static <T> List<T> traverseJsonSchemaWithFilteredCollector(final JsonNode jsonSchema,
                                                                    final BiFunction<JsonNode, List<FieldNameOrList>, Optional<T>> mapper) {
    final List<T> collector = new ArrayList<>();
    traverseJsonSchema(jsonSchema,
        (node, path) -> mapper.apply(node, path).ifPresent(collector::add));
    return collector.stream().toList(); // make list unmodifiable
  }

  /**
   * Recursive, depth-first implementation of { @link JsonSchemas#traverseJsonSchema(final JsonNode
   * jsonNode, final BiConsumer<JsonNode, List<String>> consumer) }. Takes path as argument so that
   * the path can be passed to the consumer.
   *
   * @param jsonSchemaNode - jsonschema object to traverse.
   * @param consumer - consumer to be called at each node. it accepts the current node and the path to
   *        the node from the root of the object passed at the root level invocation
   */
  @SuppressWarnings("PMD.ForLoopCanBeForeach")
  private static void traverseJsonSchemaInternal(final JsonNode jsonSchemaNode,
                                                 final List<FieldNameOrList> path,
                                                 final BiConsumer<JsonNode, List<FieldNameOrList>> consumer) {
    if (!jsonSchemaNode.isObject()) {
      throw new IllegalArgumentException(
          String.format("json schema nodes should always be object nodes. path: %s actual: %s", path, jsonSchemaNode));
    }
    consumer.accept(jsonSchemaNode, path);
    // if type is missing assume object. not official JsonSchema, but it seems to be a common
    // compromise.
    final List<String> nodeTypes = getTypeOrObject(jsonSchemaNode);

    for (final String nodeType : nodeTypes) {
      switch (nodeType) {
        // case BOOLEAN_TYPE, NUMBER_TYPE, STRING_TYPE, NULL_TYPE -> do nothing after consumer.accept above.
        case ARRAY_TYPE -> {
          final List<FieldNameOrList> newPath = new ArrayList<>(List.copyOf(path));
          newPath.add(FieldNameOrList.list());
          if (jsonSchemaNode.has(JSON_SCHEMA_ITEMS_KEY)) {
            // hit every node.
            traverseJsonSchemaInternal(jsonSchemaNode.get(JSON_SCHEMA_ITEMS_KEY), newPath, consumer);
          } else {
            log.warn("The array is missing an items field. The traversal is silently stopped. Current schema: " + jsonSchemaNode);
          }
        }
        case OBJECT_TYPE -> {
          final Optional<String> comboKeyWordOptional = getKeywordIfComposite(jsonSchemaNode);
          if (jsonSchemaNode.has(JSON_SCHEMA_PROPERTIES_KEY)) {
            for (final Iterator<Entry<String, JsonNode>> it = jsonSchemaNode.get(
                JSON_SCHEMA_PROPERTIES_KEY).fields(); it.hasNext();) {
              final Entry<String, JsonNode> child = it.next();
              final List<FieldNameOrList> newPath = new ArrayList<>(List.copyOf(path));
              newPath.add(FieldNameOrList.fieldName(child.getKey()));
              traverseJsonSchemaInternal(child.getValue(), newPath, consumer);
            }
          } else if (comboKeyWordOptional.isPresent()) {
            for (final JsonNode arrayItem : jsonSchemaNode.get(comboKeyWordOptional.get())) {
              traverseJsonSchemaInternal(arrayItem, path, consumer);
            }
          } else {
            log.warn("The object is a properties key or a combo keyword. The traversal is silently stopped. Current schema: " + jsonSchemaNode);
          }
        }
      }
    }
  }

  /**
   * If the object uses JSONSchema composite functionality (e.g. oneOf, anyOf, allOf), detect it and
   * return which one it is using.
   *
   * @param node - object to detect use of composite functionality.
   * @return the composite functionality being used, if not using composite functionality, empty.
   */
  private static Optional<String> getKeywordIfComposite(final JsonNode node) {
    for (final String keyWord : COMPOSITE_KEYWORDS) {
      if (node.has(keyWord)) {
        return Optional.ofNullable(keyWord);
      }
    }
    return Optional.empty();
  }

  /**
   * Same logic as {@link #getType(JsonNode)} except when no type is found, it defaults to type:
   * Object.
   *
   * @param jsonSchema - JSONSchema object
   * @return type of the node.
   */
  public static List<String> getTypeOrObject(final JsonNode jsonSchema) {
    final List<String> types = getType(jsonSchema);
    if (types.isEmpty()) {
      return List.of(OBJECT_TYPE);
    } else {
      return types;
    }
  }

  /**
   * Get the type of JSONSchema node. Uses JSONSchema types. Only returns the type of the "top-level"
   * node. e.g. if more nodes are nested underneath because it is an object or an array, only the top
   * level type is returned.
   *
   * @param jsonSchema - JSONSchema object
   * @return type of the node.
   */
  public static List<String> getType(final JsonNode jsonSchema) {
    if (jsonSchema.has(JSON_SCHEMA_TYPE_KEY)) {
      if (jsonSchema.get(JSON_SCHEMA_TYPE_KEY).isArray()) {
        final var iter = jsonSchema.get(JSON_SCHEMA_TYPE_KEY).iterator();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false)
            .map(JsonNode::asText)
            .toList();
      } else {
        return List.of(jsonSchema.get(JSON_SCHEMA_TYPE_KEY).asText());
      }
    }
    if (jsonSchema.has(JSON_SCHEMA_ENUM_KEY)) {
      return List.of(STRING_TYPE);
    }
    return Collections.emptyList();
  }

  private static Stream<Path> listResources(final Class<?> klass, final String name) throws IOException {
    Preconditions.checkNotNull(klass);
    Preconditions.checkNotNull(name);
    Preconditions.checkArgument(!name.isBlank());

    try {
      final String rootedResourceDir = !name.startsWith("/") ? String.format("/%s", name) : name;
      final URL url = klass.getResource(rootedResourceDir);
      // noinspection ConstantConditions
      Preconditions.checkNotNull(url, "Could not find resource.");

      final Path searchPath;
      if (url.toString().startsWith("jar")) {
        final FileSystem fileSystem = FileSystems.newFileSystem(url.toURI(), Collections.emptyMap());
        searchPath = fileSystem.getPath(rootedResourceDir);

        return Files.walk(searchPath, 1)
            .onClose(() -> {
              try {
                fileSystem.close();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });
      } else {
        searchPath = Path.of(url.toURI());
        return Files.walk(searchPath, 1);
      }
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Provides a basic scheme for describing the path into a JSON object. Each element in the path is
   * either a field name or a list.
   * <p>
   * This class is helpful in the case where fields can be any UTF-8 string, so the only simple way to
   * keep track of the different parts of a path without going crazy with escape characters is to keep
   * it in a list with list set aside as a special case.
   * <p>
   * We prefer using this scheme instead of JSONPath in the tree traversal because, it is easier to
   * decompose a path in this scheme than it is in JSONPath. Some callers of the traversal logic want
   * to isolate parts of the path easily without the need for complex regex (that would be required if
   * we used JSONPath).
   */
  public static class FieldNameOrList {

    private final String fieldName;
    private final boolean isList;

    public static FieldNameOrList fieldName(final String fieldName) {
      return new FieldNameOrList(fieldName);
    }

    public static FieldNameOrList list() {
      return new FieldNameOrList(null);
    }

    private FieldNameOrList(final String fieldName) {
      isList = fieldName == null;
      this.fieldName = fieldName;
    }

    public String getFieldName() {
      Preconditions.checkState(!isList, "cannot return field name, is list node");
      return fieldName;
    }

    public boolean isList() {
      return isList;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FieldNameOrList)) {
        return false;
      }
      final FieldNameOrList that = (FieldNameOrList) o;
      return isList == that.isList && Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
      return Objects.hash(fieldName, isList);
    }

    @Override
    public String toString() {
      return "FieldNameOrList{" +
          "fieldName='" + fieldName + '\'' +
          ", isList=" + isList +
          '}';
    }

  }

}
