package com.adilhanney.wingedsandals

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.api.ModInitializer
import net.minecraft.resources.ResourceLocation
import org.slf4j.LoggerFactory

object WingedSandals : ModInitializer {
  const val MOD_ID = "wingedsandals"
  val logger = LoggerFactory.getLogger(MOD_ID)!!

  override fun onInitialize() {
    logger.info("Hello Fabric world!")
    ModItems.registerItems()
  }

  /** @return the identifier of [path] in this mod's namespace */
  //? if >=1.21 {
  fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
  //?} else {
  /*fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
  *///?}
}
