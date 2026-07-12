package com.adilhanney.wingedsandals

//? if fabric {
import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.api.ModInitializer

object WingedSandalsFabric : ModInitializer {
  override fun onInitialize() {
    WingedSandals.logger.info("Hello Fabric world!")
    ModItems.registerItems()
  }
}
//?}
