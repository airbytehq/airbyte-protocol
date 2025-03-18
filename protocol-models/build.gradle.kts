import org.jsonschema2pojo.SourceType

plugins {
    id("io.airbyte.gradle.jvm.lib")
    id("com.github.eirnym.js2p")
}

dependencies {
    implementation(platform(libs.fasterxml))
    implementation(libs.bundles.jackson)
    implementation(libs.commons.lang3)
    implementation(libs.guava)
    implementation(libs.slf4j.api)
    implementation(libs.validation.api)
}

airbyte {
    pmd {
        ruleFiles.set(project.rootProject.files("./tools/gradle/pmd/rules.xml"))
    }
}

jsonSchema2Pojo {
    setSourceType(SourceType.YAMLSCHEMA.name)
    setSource(files("${sourceSets["main"].output.resourcesDir}/airbyte_protocol"))
    targetDirectory = file("${project.layout.buildDirectory.get()}/generated/src/gen/java/")
    targetPackage = "io.airbyte.protocol.models"
    useLongIntegers = true
    removeOldOutput = true
    generateBuilders = true
    includeConstructors = false
    includeSetters = true
    serializable = true
}

tasks.register<Exec>("generatePythonPydanticV1ClassFiles") {
    inputs.dir("${sourceSets["main"].output.resourcesDir}/airbyte_protocol")
    inputs.dir("bin")
    outputs.dir("python/airbyte_protocol/airbyte_protocol/models")
    commandLine("bin/generate-python-pydantic-docker.sh")
}

tasks.register<Exec>("generatePythonPydanticV2ProtocolClassFiles") {
    inputs.dir("${sourceSets["main"].output.resourcesDir}/airbyte_protocol")
    inputs.dir("bin")
    outputs.dir("python/airbyte_protocol_pdv2/airbyte_protocol/models")
    commandLine("bin/generate-python-pydantic-v2-docker.sh")
}

tasks.register<Exec>("generateTypescriptProtocolClassFiles") {
    inputs.dir("${sourceSets["main"].output.resourcesDir}/airbyte_protocol")
    inputs.dir("bin")
    outputs.dir("typescript/dist")
    commandLine("bin/generate-typescript-classes-docker.sh")
}

tasks.named("compileJava") {
    dependsOn(tasks.named("generateJsonSchema2Pojo"))
}