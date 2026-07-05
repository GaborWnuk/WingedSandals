package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.equipment.ArmorType

object ModItems {
  val wingedSandals: Item = register("winged_sandals", ::WingedSandalsItem,
    Item.Properties()
      .rarity(Rarity.UNCOMMON)
      .durability(ArmorType.BOOTS.getDurability(7))
  )

  private fun register(name: String, itemFactory: (Item.Properties) -> Item, properties: Item.Properties): Item {
    val registryKey =
      ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WingedSandals.MOD_ID, name))

    return Registry.register(BuiltInRegistries.ITEM, registryKey, itemFactory(properties.setId(registryKey)))
  }

  fun registerItems() {
    WingedSandals.logger.info("Registering Items for " + WingedSandals.MOD_ID)

    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register { output ->
      output.accept(wingedSandals)
    }

    ServerTickEvents.END_SERVER_TICK.register {
      for (player in it.playerList.players) {
        WingedSandalsItem.setAllowFlying(player)
      }
    }
  }
}
