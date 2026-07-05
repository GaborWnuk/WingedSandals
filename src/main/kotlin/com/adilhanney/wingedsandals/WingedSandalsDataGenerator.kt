package com.adilhanney.wingedsandals

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
