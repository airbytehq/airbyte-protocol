import java.util.Properties

// The buildscript block defines dependencies in order for .gradle file evaluation.
// This is separate from application dependencies.
// See https://stackoverflow.com/questions/17773817/purpose-of-buildscript-block-in-gradle.
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

plugins {
    id("base")
    id("io.airbyte.gradle.jvm") version "0.56.0" apply false
    id("io.airbyte.gradle.jvm.lib") version "0.56.0" apply false
    id("io.airbyte.gradle.publish") version "0.56.0" apply false
    id("com.github.hierynomus.license") version "0.16.1"
    id("version-catalog")
}

repositories {
    maven {
        name = "cloudrepo"
        url = uri("https://airbyte.mycloudrepo.io/repositories/airbyte-public-jars")
        credentials {
            username = System.getenv("CLOUDREPO_USER")
            password = System.getenv("CLOUDREPO_PASSWORD")
        }
    }
    mavenCentral()
    maven {
        url = uri("https://airbyte.mycloudrepo.io/public/repositories/airbyte-public-jars/")
    }
}

val env = Properties()
rootProject.file(".env").inputStream().use {
    env.load(it)
}

allprojects {
    apply(plugin = "base")

    group = "io.airbyte.${rootProject.name}"
    version = System.getenv("VERSION") ?: env["VERSION"].toString()
}
