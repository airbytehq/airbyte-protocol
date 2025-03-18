pluginManagement {
    repositories {
        maven {
            name = "localPluginRepo"
            url = uri("${System.getProperty("user.home")}/.airbyte/gradle")
        }
        maven(url = "https://airbyte.mycloudrepo.io/public/repositories/airbyte-public-jars")
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

// Configure the gradle enterprise plugin to enable build scans. Enabling the plugin at the top of the settings file allows the build scan to record
// as much information as possible.
plugins {
    id("com.gradle.enterprise") version "3.4.1"
    id("com.github.burrunan.s3-build-cache") version "1.5"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

val isCiServer = System.getenv().containsKey("CI")

buildCache {
    local {
        // Local build cache is dangerous as it might produce inconsistent results
        // in case developer modifies files while the build is running
        isEnabled = false
    }
    remote<com.github.burrunan.s3cache.AwsS3BuildCache> {
        region = "us-east-2"
        bucket = "airbyte-buildcache"
        prefix = "cache/"
        isPush = isCiServer
        isEnabled = System.getenv().containsKey("S3_BUILD_CACHE_ACCESS_KEY_ID")
    }
}

rootProject.name = "airbyte-protocol"

// definition for dependency resolution
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://airbyte.mycloudrepo.io/public/repositories/airbyte-public-jars/")
        }
    }
}

include(":protocol-models")
