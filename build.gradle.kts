plugins {
    java
    `maven-publish`
    signing
    kotlin("jvm") version "2.0.20"
}

val repoUri = "https://github.com/sharedwonder/hyplookup"
val pkgUri = "https://maven.pkg.github.com/sharedwonder/maven-repository"

group = "net.sharedwonder"
version = property("version").toString()

repositories {
    mavenLocal()
    mavenCentral()
    maven(pkgUri)
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

publishing {
    repositories {
        mavenLocal()
        maven(pkgUri) {
            name = "GitHubPackages"
            credentials {
                username = findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name = project.name
                description = project.description
                url = repoUri

                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                    }
                }

                developers {
                    developer {
                        id = "sharedwonder"
                        name = "Liu Baihao"
                        email = "liubaihaohello@outlook.com"
                    }
                }

                scm {
                    connection = "scm:git:$repoUri.git"
                    developerConnection = "scm:git:$repoUri.git"
                    url = repoUri
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
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
