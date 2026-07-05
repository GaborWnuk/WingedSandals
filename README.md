# WingedSandals

A Fabric mod that lets you have creative-style flight in survival
at the cost of your elytra.

## About this fork

This is a fork of [adil192/WingedSandals](https://github.com/adil192/WingedSandals),
updated to run on modern Minecraft (26.1.2 and newer).

The reason is simple: my 6-year-old son loves mobs and survival mode, but he
wanted to be able to fly like in creative mode. The original mod does exactly
that — it just hadn't been updated past Minecraft 1.21.5, so this fork brings
it to current versions.

## Crafting

Craft the winged sandals by combining a pair of golden boots and an elytra.

![A crafting table interface with an elytra above some golden boots](.github/gallery/crafting_recipe.png)

## Usage

Simply wear the winged sandals and double-jump to start flying!

## Supported versions

| Minecraft        | Where                                                                              |
| ---------------- | ---------------------------------------------------------------------------------- |
| 26.1.2 or newer  | this fork (`main`) — tested on 26.1.2                                              |
| 1.20 – 1.21.5    | [upstream releases](https://github.com/adil192/WingedSandals/releases) or the `1.20.x-1.21.x` branch |

Minecraft versions older than 26.1.2 are not supported by this fork: the mod
declares `minecraft: ~26.1.2` and `java: >=25` in its metadata, so Fabric
Loader will refuse to load it on older versions instead of crashing.

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
- **Multi-version setup**: Stonecutter 0.9 with a centralized
  [`stonecutter.properties.toml`](stonecutter.properties.toml) instead of
  per-version `gradle.properties`. Support for 1.20.x–1.21.x was moved to the
  `1.20.x-1.21.x` branch.
- **Resources**: armor textures are plain files at the modern
  `textures/entity/equipment/humanoid/` path (the old symlink-based layout,
  which never worked well across Windows/WSL, is gone).
- **CI**: builds and publishes on Java 25.

## Building

```sh
./gradlew build
```

The jar ends up in `versions/26.1.2/build/libs/`. Gradle provisions the
required JDK 25 automatically (see `gradle/gradle-daemon-jvm.properties`).

To regenerate data files (models, recipes, advancements):

```sh
./gradlew :26.1.2:runDatagen
```

## Credits

Original mod by [adil192](https://github.com/adil192) — thank you!
Licensed under the [MIT license](LICENSE).
