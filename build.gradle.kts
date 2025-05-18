plugins {
    java
    kotlin("jvm") version "2.1.10"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("net.sharedwonder:lightproxy:0.1.0")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("io.netty:netty-buffer:4.2.1.Final")
    implementation("org.apache.logging.log4j:log4j-api:2.24.1")
    implementation("org.jspecify:jspecify:1.0.0")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xjvm-default=all")
}

tasks.withType(JavaCompile::class).configureEach {
    options.encoding = "UTF-8"
}

tasks.processResources {
    filesMatching("lightproxy-addon.json") {
        expand("version" to version)
    }
}
