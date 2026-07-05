package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
  FabricRecipeProvider(output, registriesFuture) {

  override fun createRecipeProvider(registries: HolderLookup.Provider, exporter: RecipeOutput): RecipeProvider {
    return object : RecipeProvider(registries, exporter) {
      override fun buildRecipes() {
        shapeless(RecipeCategory.COMBAT, ModItems.wingedSandals)
          .requires(Items.GOLDEN_BOOTS)
          .requires(Items.ELYTRA)
          .unlockedBy(getHasName(Items.ELYTRA), has(Items.ELYTRA))
          .save(exporter)
      }
    }
  }

  override fun getName(): String = "WingedSandalsRecipeProvider"
}
