package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
//? if >=26.1 {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
//?} else {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput as FabricPackOutput
*///?}
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.world.item.Items
//? if >=1.20.5 {
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeOutput
import java.util.concurrent.CompletableFuture
//?} else {
/*import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer
*///?}
//? if >=1.21.2 {
import net.minecraft.data.recipes.RecipeProvider
//?} else {
/*import net.minecraft.data.recipes.ShapelessRecipeBuilder
*///?}

//? if >=1.21.2 {
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
//?} else if >=1.20.5 {
/*class ModRecipeProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
  FabricRecipeProvider(output, registriesFuture) {

  override fun buildRecipes(exporter: RecipeOutput) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.wingedSandals)
      .requires(Items.GOLDEN_BOOTS)
      .requires(Items.ELYTRA)
      .unlockedBy(getHasName(Items.ELYTRA), has(Items.ELYTRA))
      .save(exporter)
  }
}
*///?} else {
/*class ModRecipeProvider(output: FabricPackOutput) : FabricRecipeProvider(output) {

  override fun buildRecipes(exporter: Consumer<FinishedRecipe>) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.wingedSandals)
      .requires(Items.GOLDEN_BOOTS)
      .requires(Items.ELYTRA)
      .unlockedBy(getHasName(Items.ELYTRA), has(Items.ELYTRA))
      .save(exporter)
  }
}
*///?}
