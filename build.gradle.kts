plugins {
    java
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.freefair.lombok") version "6.3.0"
}

group = "com.github.rephrasing"
version = "1.8.8"


repositories {
    mavenCentral()
    maven {
        name = "spigot"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    // spigot api
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    // mongodb
    api("org.mongodb:mongodb-driver-sync:4.6.0")

    implementation("org.reflections:reflections:0.10.2")
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar").configure {

    minimize()
    archiveFileName.set("${project.name}-v${project.version}.jar")
    destinationDirectory.set(file("$rootDir/output"))
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}