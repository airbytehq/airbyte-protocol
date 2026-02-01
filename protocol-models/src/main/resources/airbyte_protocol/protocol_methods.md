# Protocol Methods

The purpose of this doc is to define the interface on the docker containers that implement the Airbyte Protocol. It is purposefully lean, as we describe the protocol at length in [our docs](https://docs.airbyte.com/understanding-airbyte/airbyte-protocol). We just want to define the interface here, so that as it evolves, we can version the change as part of the protocol version.

## Method Interfaces

We describe these interfaces in pseudocode for clarity. Clarifications on the pseudocode semantics:
* Any `Stream~ that is mentioned as input arg, is passed to the docker contained via STDIN. 
* All other parameters are passed in as command line args (e.g. --config <path to config file>). 
* Each input parameter is described as its type (as defined in airbyte_protocol.yml and the name of the parameter). 
* If an input parameter has no name, then it is passed via STDIN.

In addition to the return types mentioned below, all methods can return the following message types: `AirbyteLogMessage | AirbyteTraceMessage`.

**interface of both source and destination**
```
spec() -> Stream<AirbyteConnectorSpecification>
check(Config, Optional<ConfiguredCatalog>) -> Stream<AirbyteConnectionStatus>
```
**source only**
```
discover(Config) -> AirbyteCatalog
read(Config, ConfiguredAirbyteCatalog, State) -> Stream<AirbyteRecordMessage | AirbyteStateMessage | AirbyteControlMessage>
```
**destination only**
```
write(Config config, AirbyteCatalog catalog, Stream<AirbyteMessage>) -> Stream<AirbyteStateMessage | AirbyteControlMessage?
```
