# WingedSandals

A Fabric and NeoForge mod that lets you have creative-style flight in survival
at the cost of your elytra.

## About this fork

This is a fork of [adil192/WingedSandals](https://github.com/adil192/WingedSandals),
updated to run on modern Minecraft (26.1.2) while still supporting every
version the original did.

The reason is simple: my 6-year-old son loves mobs and survival mode, but he
wanted to be able to fly like in creative mode. The original mod does exactly
that — it just hadn't been updated past Minecraft 1.21.5, so this fork brings
it to current versions from the same codebase that still builds the older ones.

## Crafting

Craft the winged sandals by combining a pair of golden boots and an elytra.

![A crafting table interface with an elytra above some golden boots](.github/gallery/crafting_recipe.png)

## Usage

Simply wear the winged sandals and double-jump to start flying!

## Supported versions

| Minecraft         | Fabric | NeoForge | Java |
| ----------------- | :----: | :------: | :--: |
| 26.1.2            |   ✓    |    ✓     |  25  |
| 1.21.5            |   ✓    |    ✓     |  21  |
| 1.21.4            |   ✓    |    ✓     |  21  |
| 1.21.2 – 1.21.3   |   ✓    |    ✓     |  21  |
| 1.21 – 1.21.1     |   ✓    |    ✓     |  21  |
| 1.20.5 – 1.20.6   |   ✓    |          |  21  |
| 1.20 – 1.20.4     |   ✓    |          |  17  |

Each row is a separate jar; the Minecraft range is part of its file name
(e.g. `wingedsandals-fabric-1.3.0+1.21-1.21.1.jar`). Each jar also declares
its range in its metadata, so the loader refuses a jar built for another
version instead of crashing.

Required companion mods:

- **Fabric**: Fabric API and Fabric Language Kotlin
- **NeoForge**: Kotlin for Forge (5.12+ on 1.21.x, 6.3+ on 26.1)

## What changed in the 26.1 update

Minecraft 26.1 was the largest modding break in years, so the port touched
nearly everything:

- **Toolchain**: Gradle 9.6, Java 25, Kotlin 2.4, and the new
  `net.fabricmc.fabric-loom` plugin. Since 26.1 the game is no longer
  obfuscated — Yarn mappings are gone and the code now uses Mojang's official
  names.
- **Code**: all sources ported to the 26.1 APIs, e.g. `Identifier` (formerly
  `ResourceLocation`), `CreativeModeTabEvents.modifyOutputEvent` (formerly
  `ItemGroupEvents.modifyEntriesEvent`), direct `Registry.register` item
  registration, and the `FabricPackOutput` data-generation API.
- **Multi-version, multiloader setup**: Stonecutter 0.9 with a centralized
  [`stonecutter.properties.toml`](stonecutter.properties.toml) and one
  codebase for every target — each `versions/{minecraft}-{loader}` node
  builds from the same sources, with version and loader differences behind
  Stonecutter conditionals.
- **Mojang names everywhere**: Minecraft is obfuscated before 26.1, so the
  older Fabric nodes use the remapping `net.fabricmc.fabric-loom-remap`
  plugin with Mojang's official mappings. That gives them the same names as
  the 26.1 and NeoForge code, instead of Yarn.
- **Resources**: the armor texture and equipment model are plain files at
  their 26.1 paths; the build moves them to wherever each older version looks
  for them (the old symlink-based layout, which never worked well across
  Windows/WSL, is gone).
- **CI**: builds every node; Gradle runs on Java 25 and older targets compile
  on Java 17 or 21.

## Building

```sh
./gradlew build
```

This builds every version and loader; the jars end up in
`versions/{minecraft}-{loader}/build/libs/`. Gradle provisions JDK 25 for
itself and JDK 17/21 for the older targets automatically (see
`gradle/gradle-daemon-jvm.properties`).

To build or run a single target:

```sh
./gradlew :1.21.1-neoforge:build
./gradlew :1.20.1-fabric:runClient
```

Each target gets its own game folder under `run/{minecraft}-{loader}`.

To regenerate data files (models, recipes, advancements) for a Minecraft
version, run its Fabric node; the NeoForge node of the same version reuses
the output:

```sh
./gradlew :26.1.2-fabric:runDatagen
```

## Credits

Original mod by [adil192](https://github.com/adil192) — thank you!
Licensed under the [MIT license](LICENSE).
