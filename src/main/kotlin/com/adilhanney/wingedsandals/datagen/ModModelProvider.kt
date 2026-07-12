package com.adilhanney.wingedsandals.datagen

//? if fabric {
import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates

class ModModelProvider(output: FabricPackOutput) : FabricModelProvider(output) {
  override fun generateBlockStateModels(generator: BlockModelGenerators) {
  }

  override fun generateItemModels(generator: ItemModelGenerators) {
    generator.generateFlatItem(ModItems.wingedSandals, ModelTemplates.FLAT_ITEM)
  }
}
//?}
