package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorMaterials
import net.minecraft.world.item.equipment.EquipmentAssets

object ModArmorMaterials {
  private val gold = ArmorMaterials.GOLD
  private val netherite = ArmorMaterials.NETHERITE

  val id = Identifier.fromNamespaceAndPath(WingedSandals.MOD_ID, "winged_sandals")!!

  val wingedSandalsMaterial = ArmorMaterial(
    7,
    gold.defense(),
    gold.enchantmentValue(),
    gold.equipSound(),
    gold.toughness(),
    netherite.knockbackResistance(),
    ItemTags.REPAIRS_GOLD_ARMOR,
    ResourceKey.create(EquipmentAssets.ROOT_ID, id),
  )
}
