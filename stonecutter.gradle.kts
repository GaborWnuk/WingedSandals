plugins {
    id("dev.kikugie.stonecutter")
    // Declared once here so every version node shares the same plugin classloader
    id("org.jetbrains.kotlin.jvm") version "2.4.0" apply false
    id("net.fabricmc.fabric-loom-remap") version "1.17-SNAPSHOT" apply false
}
stonecutter active "1.20.1-fabric"

stonecutter parameters {
    val (version, loader) = current.project.split('-', limit = 2)

    // Makes version- and loader-specific properties apply from `stonecutter.properties.toml`
    properties {
        tags(version, loader)
    }
}
