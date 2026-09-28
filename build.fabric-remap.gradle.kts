plugins {
	id("org.jetbrains.kotlin.jvm")
	// Minecraft before 26.1 is obfuscated: remap it (and mod dependencies) to
	// Mojang's official names so all targets share the same source names
	id("net.fabricmc.fabric-loom-remap")
}

val javaVersion = JavaVersion.toVersion(property("deps.java")!!)
val fabricApiVersion: String = sc.properties["deps.fabric_api"]
val mcVersionRangeForFabric: String = sc.properties["mod.mc_compat"]
val fabricLoaderMin: String = sc.properties["deps.fabric_loader_min"]

version = "${property("mod.version")}+${property("mod.mc_label")}"

base {
	archivesName = "${property("mod.id")}-fabric"
}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${sc.current.version}")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
	modImplementation("net.fabricmc:fabric-language-kotlin:${property("deps.fabric_kotlin")}")
}

tasks {
	processResources {
		inputs.property("java", javaVersion.majorVersion)
		inputs.property("minecraftVersionRange", mcVersionRangeForFabric)
		inputs.property("fabricLoaderMin", fabricLoaderMin)
		inputs.property("version", project.version)

		filesMatching("fabric.mod.json") {
			expand(mapOf(
				"java" to inputs.properties["java"],
				"minecraftVersionRange" to inputs.properties["minecraftVersionRange"],
				"fabricLoaderMin" to inputs.properties["fabricLoaderMin"],
				"version" to inputs.properties["version"],
			))
		}
	}

	jar {
		inputs.property("archivesName", project.base.archivesName)

		from(rootProject.file("LICENSE")) {
			rename { "${it}_${inputs.properties["archivesName"]}"}
		}
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(javaVersion.majorVersion.toInt())
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	compilerOptions {
		jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(javaVersion.majorVersion)
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion

	toolchain {
		languageVersion = JavaLanguageVersion.of(javaVersion.majorVersion)
	}
}

loom {
	runConfigs.all {
		generateRunConfig = true // Run configurations are not created for subprojects by default
		// Each target gets its own game folder, as their configs and worlds are not interchangeable
		runDirectory = rootProject.file("run/${project.name}")
	}
	runConfigs.named("server") {
		runDirectory = rootProject.file("run/${project.name}/server")
	}
}
