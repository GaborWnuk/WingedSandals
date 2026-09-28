package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
//? if >=26.1 {
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
//?} else {
/*import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
*///?}
//? if >=1.21.2 {
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
//?}
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
//? if >=1.21.2 {
import net.minecraft.world.item.equipment.ArmorType
//?} else if >=1.20.5 {
/*import net.minecraft.world.item.ArmorItem
*///?}

object ModItems {
  // Before 1.20.5 the durability comes from the armor material
  private fun itemProperties() = Item.Properties()
    .rarity(Rarity.UNCOMMON)
    //? if >=1.21.2 {
    .durability(ArmorType.BOOTS.getDurability(7))
    //?} else if >=1.20.5 {
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

    //? if >=26.1 {
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register { output ->
      output.accept(wingedSandals)
    }
    //?} else {
    /*ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
      entries.accept(wingedSandals)
    }
    *///?}

    ServerTickEvents.END_SERVER_TICK.register {
      for (player in it.playerList.players) {
        WingedSandalsItem.setAllowFlying(player)
      }
    }
  }
}
