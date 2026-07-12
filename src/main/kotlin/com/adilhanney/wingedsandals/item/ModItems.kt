package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
//? if fabric {
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
//?} else {
/*import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
*///?}
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.equipment.ArmorType

object ModItems {
  private fun itemProperties() = Item.Properties()
    .rarity(Rarity.UNCOMMON)
    .durability(ArmorType.BOOTS.getDurability(7))

  //? if fabric {
  val wingedSandals: Item = register("winged_sandals", ::WingedSandalsItem, itemProperties())

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
  //?} else {
  /*private val ITEMS = DeferredRegister.createItems(WingedSandals.MOD_ID)

  val wingedSandals: DeferredItem<WingedSandalsItem> =
    ITEMS.registerItem("winged_sandals", ::WingedSandalsItem, Supplier { itemProperties() })

  fun register(modEventBus: IEventBus) {
    WingedSandals.logger.info("Registering Items for " + WingedSandals.MOD_ID)

    ITEMS.register(modEventBus)

    modEventBus.addListener<BuildCreativeModeTabContentsEvent> { event ->
      if (event.tabKey == CreativeModeTabs.COMBAT) event.accept(wingedSandals)
    }

    NeoForge.EVENT_BUS.addListener(WingedSandalsItem::onBootsChanged)
  }
  *///?}
}
