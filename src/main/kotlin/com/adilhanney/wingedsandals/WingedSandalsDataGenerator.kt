package com.adilhanney.wingedsandals

// Data generation runs on the Fabric target only; the generated JSON is
// plain vanilla data shared by both loaders.
//? if fabric {
import com.adilhanney.wingedsandals.datagen.ModModelProvider
import com.adilhanney.wingedsandals.datagen.ModRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class WingedSandalsDataGenerator : DataGeneratorEntrypoint {
  override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
    val pack = fabricDataGenerator.createPack()
    pack.addProvider(::ModModelProvider)
    pack.addProvider(::ModRecipeProvider)
  }
}
//?}
