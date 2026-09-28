package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
//? if >=1.21.4 {
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
//?} else {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
*///?}

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
  override fun generateBlockStateModels(generator: BlockModelGenerators) {
  }

  override fun generateItemModels(generator: ItemModelGenerators) {
    generator.generateFlatItem(ModItems.wingedSandals, ModelTemplates.FLAT_ITEM)
  }
}
