package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
//? if >=1.21.2 {
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
//?}
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
//? if >=1.21.2 {
import net.minecraft.world.item.equipment.ArmorType
//?} else if >=1.21 {
/*import net.minecraft.world.item.ArmorItem
*///?}

object ModItems {
  private fun itemProperties() = Item.Properties()
    .rarity(Rarity.UNCOMMON)
    //? if >=1.21.2 {
    .durability(ArmorType.BOOTS.getDurability(7))
    //?} else if >=1.21 {
    /*.durability(ArmorItem.Type.BOOTS.getDurability(7))
    *///?}

  val wingedSandals: Item = register("winged_sandals", ::WingedSandalsItem, itemProperties())

  private fun register(name: String, itemFactory: (Item.Properties) -> Item, properties: Item.Properties): Item {
    //? if >=1.21.2 {
    val registryKey = ResourceKey.create(Registries.ITEM, WingedSandals.id(name))
    return Registry.register(BuiltInRegistries.ITEM, registryKey, itemFactory(properties.setId(registryKey)))
    //?} else {
    /*return Registry.register(BuiltInRegistries.ITEM, WingedSandals.id(name), itemFactory(properties))
    *///?}
  }

  fun registerItems() {
    WingedSandals.logger.info("Registering Items for " + WingedSandals.MOD_ID)

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
      entries.accept(wingedSandals)
    }

    ServerTickEvents.END_SERVER_TICK.register {
      for (player in it.playerList.players) {
        WingedSandalsItem.setAllowFlying(player)
      }
    }
  }
}
