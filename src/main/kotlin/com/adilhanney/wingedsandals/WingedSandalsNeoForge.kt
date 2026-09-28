package com.adilhanney.wingedsandals

//? if neoforge {
/*//? if <1.21.2
//import com.adilhanney.wingedsandals.item.ModArmorMaterials
import com.adilhanney.wingedsandals.item.ModItems
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod

@Mod(WingedSandals.MOD_ID)
class WingedSandalsNeoForge(modEventBus: IEventBus, modContainer: ModContainer) {
  init {
    WingedSandals.logger.info("Hello NeoForge world!")
    //? if <1.21.2
    //ModArmorMaterials.register(modEventBus)
    ModItems.register(modEventBus)
  }
}
*///?}
