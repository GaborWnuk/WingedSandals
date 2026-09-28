plugins {
	id("org.jetbrains.kotlin.jvm")
	id("net.neoforged.moddev")
}

val javaVersion = JavaVersion.toVersion(property("deps.java")!!)
val mcVersionRangeForNeoForge: String = sc.properties["mod.mc_compat"]
val neoForgeVersionRange: String = sc.properties["deps.neo_compat"]
val kotlinForForgeVersion: String = sc.properties["deps.kotlin_forge"]
val armorTexturePath: String = sc.properties["mod.armor_texture"]
val equipmentModelPath: String = sc.properties["mod.equipment_model"]

version = "${property("mod.version")}+${property("mod.mc_label")}"

base {
	archivesName = "${property("mod.id")}-neoforge"
}

repositories {
	maven("https://thedarkcolour.github.io/KotlinForForge/") { name = "KotlinForForge" }
}

dependencies {
	implementation("thedarkcolour:kotlinforforge-neoforge:$kotlinForForgeVersion")
}

neoForge {
	version = sc.properties["deps.neo_loader"]

	mods {
		register("wingedsandals") {
			sourceSet(sourceSets.main.get())
		}
	}

	runs {
		// Each target gets its own game folder, as their configs and worlds are not interchangeable
		val runDirectory = rootProject.file("run/${project.name}")
		register("client") {
			client()
			gameDirectory = runDirectory
		}
		register("server") {
			server()
			gameDirectory = runDirectory.resolve("server")
		}
	}
}

sourceSets.main {
	// Data generation runs on the Fabric target; the output is plain vanilla
	// JSON shared by both loaders.
	resources.srcDir(rootProject.file("versions/${sc.current.version}-fabric/src/main/generated"))
}

tasks {
	processResources {
		inputs.property("minecraftVersionRange", mcVersionRangeForNeoForge)
		inputs.property("neoForgeVersionRange", neoForgeVersionRange)
		inputs.property("kotlinForForgeVersion", kotlinForForgeVersion)
		inputs.property("version", project.version)
		inputs.property("armorTexturePath", armorTexturePath)
		inputs.property("equipmentModelPath", equipmentModelPath)

		filesMatching("META-INF/neoforge.mods.toml") {
			expand(mapOf(
				"minecraftVersionRange" to inputs.properties["minecraftVersionRange"],
				"neoForgeVersionRange" to inputs.properties["neoForgeVersionRange"],
				"kotlinForForgeVersion" to inputs.properties["kotlinForForgeVersion"],
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

		exclude("fabric.mod.json")
	}

	named("createMinecraftArtifacts") {
		dependsOn("stonecutterGenerate")
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
