plugins {
    kotlin("js") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.635")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.635")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.11.4-pre.635")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
}
