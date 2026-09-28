pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/")
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.6"
	// Lets Gradle auto-provision the JDK required by gradle/gradle-daemon-jvm.properties
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

stonecutter {
	create(rootProject) {
		/**
		 * Creates version nodes for multiple loaders, named `versions/{project}-{loader}`,
		 * each using the loader-specific `build.{loader}.gradle.kts` build script.
		 * Minecraft is obfuscated before 26.1, so Fabric targets for 1.x versions use
		 * `build.fabric-remap.gradle.kts`, which remaps the game to Mojang's official names.
		 */
		fun match(project: String, vararg loaders: String, version: String = project) {
			val obfuscated = version.startsWith("1.")
			for (loader in loaders) {
				val script = if (loader == "fabric" && obfuscated) "fabric-remap" else loader
				version("$project-$loader", version).buildscript("build.$script.gradle.kts")
			}
		}

		match("1.20.1", "fabric")
		match("1.20.5", "fabric")
		match("1.21.1", "fabric")
		match("1.21.2", "fabric")
		match("1.21.4", "fabric")
		match("1.21.5", "fabric")
		vcsVersion = "1.21.5-fabric"
	}
}
