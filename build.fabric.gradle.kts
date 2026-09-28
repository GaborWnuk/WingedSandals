plugins {
	id("org.jetbrains.kotlin.jvm")
	// Since Minecraft 26.1 the game is unobfuscated: use the non-remapping loom variant
	id("net.fabricmc.fabric-loom")
}

val javaVersion = JavaVersion.toVersion(property("deps.java")!!)
val fabricApiVersion: String = sc.properties["deps.fabric_api"]
val mcVersionRangeForFabric: String = sc.properties["mod.mc_compat"]
val fabricLoaderMin: String = sc.properties["deps.fabric_loader_min"]
val armorTexturePath: String = sc.properties["mod.armor_texture"]
val equipmentModelPath: String = sc.properties["mod.equipment_model"]

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
	implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	implementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
	implementation("net.fabricmc:fabric-language-kotlin:${property("deps.fabric_kotlin")}")
}

tasks {
	processResources {
		inputs.property("java", javaVersion.majorVersion)
		inputs.property("minecraftVersionRange", mcVersionRangeForFabric)
		inputs.property("fabricLoaderMin", fabricLoaderMin)
		inputs.property("version", project.version)
		inputs.property("armorTexturePath", armorTexturePath)
		inputs.property("equipmentModelPath", equipmentModelPath)

		filesMatching("fabric.mod.json") {
			expand(mapOf(
				"java" to inputs.properties["java"],
				"minecraftVersionRange" to inputs.properties["minecraftVersionRange"],
				"fabricLoaderMin" to inputs.properties["fabricLoaderMin"],
				"version" to inputs.properties["version"],
			))
		}

		// The sources keep the armor assets at their 26.1 paths; move them to
		// wherever this Minecraft version looks for them
		filesMatching("assets/wingedsandals/textures/entity/equipment/humanoid/winged_sandals.png") {
			path = armorTexturePath
		}
		filesMatching("assets/wingedsandals/equipment/winged_sandals.json") {
			path = equipmentModelPath
		}

		exclude("META-INF/neoforge.mods.toml")
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
