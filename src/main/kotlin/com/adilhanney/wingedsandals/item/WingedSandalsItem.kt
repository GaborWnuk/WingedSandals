package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
//? if >=1.21.5 {
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.item.Item
//?} else {
/*import net.minecraft.world.item.ArmorItem
import net.minecraft.world.level.Level
*///?}
//? if >=1.21.2
import net.minecraft.world.item.equipment.ArmorType

//? if >=1.21.5 {
class WingedSandalsItem(properties: Properties) : Item(properties.humanoidArmor(
  ModArmorMaterials.wingedSandalsMaterial,
  ArmorType.BOOTS,
)) {
//?} else if >=1.21.2 {
/*class WingedSandalsItem(properties: Properties) : ArmorItem(
  ModArmorMaterials.wingedSandalsMaterial,
  ArmorType.BOOTS,
  properties,
) {
*///?} else {
/*class WingedSandalsItem(properties: Properties) : ArmorItem(
  ModArmorMaterials.wingedSandalsMaterial,
  Type.BOOTS,
  properties,
) {
*///?}

  //? if >=1.21.5 {
  override fun inventoryTick(stack: ItemStack, level: ServerLevel, entity: Entity, slot: EquipmentSlot?) {
    if (entity is Player) setAllowFlying(entity)
    super.inventoryTick(stack, level, entity, slot)
  }
  //?} else {
  /*override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
    if (entity is Player) setAllowFlying(entity)
    super.inventoryTick(stack, level, entity, slotId, isSelected)
  }
  *///?}

  companion object {
    /** @return Whether the player would be able to fly in vanilla minecraft */
    private fun canNormallyFly(player: Player): Boolean {
      return player.isCreative || player.isSpectator
    }

    /** Sets the player's ability to fly based on whether they have the winged sandals equipped. */
    fun setAllowFlying(player: Player) {
      val itemStack = player.getItemBySlot(EquipmentSlot.FEET)
      val isEquipped = itemStack.item is WingedSandalsItem
      setAllowFlying(player, isEquipped)
    }

    /** Sets the player's ability to fly based on whether they have the winged sandals equipped. */
    fun setAllowFlying(player: Player, isEquipped: Boolean) {
      val allowFlying = isEquipped || canNormallyFly(player)
      if (player.abilities.mayfly == allowFlying) return

      if (allowFlying) {
        WingedSandals.logger.info("${player.name.string} can now fly with their winged sandals!")
      } else {
        WingedSandals.logger.info("${player.name.string} can no longer fly!")
      }

      player.abilities.mayfly = allowFlying
      if (!allowFlying) player.abilities.flying = false
      player.onUpdateAbilities()
    }
  }
}
