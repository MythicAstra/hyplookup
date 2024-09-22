plugins {
    kotlin("jvm") version "2.0.20"
}

group = "net.sharedwonder"
version = property("version").toString()

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("net.sharedwonder:lightproxy:0.1.0")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("io.netty:netty-buffer:4.1.112.Final")
    implementation("org.apache.logging.log4j:log4j-api:2.24.0")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.3")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xjvm-default=all")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.processResources {
    filesMatching("lightproxy-addon.json") {
        expand("version" to version)
    }
}

tasks.test {
    useJUnitPlatform()
}
