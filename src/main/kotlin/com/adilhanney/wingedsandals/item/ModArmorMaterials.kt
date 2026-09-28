package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
//? if >=1.21.2 {
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorMaterials
//?} else {
/*import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ArmorMaterials
*///?}
//? if >=1.21.4 {
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.equipment.EquipmentAssets
//?}
//? if >=1.20.5 && <1.21.2 {
/*import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
*///?}
//? if <1.21.2 {
/*import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
*///?}
//? if <1.20.5 {
/*import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.ArmorItem
*///?}

/** Gold armor stats with netherite's knockback resistance. */
object ModArmorMaterials {
  private const val NAME = "winged_sandals"

  //? if >=1.21.2 {
  private val gold = ArmorMaterials.GOLD
  private val netherite = ArmorMaterials.NETHERITE

  val wingedSandalsMaterial = ArmorMaterial(
    7,
    gold.defense(),
    gold.enchantmentValue(),
    gold.equipSound(),
    gold.toughness(),
    netherite.knockbackResistance(),
    ItemTags.REPAIRS_GOLD_ARMOR,
    //? if >=1.21.4 {
    ResourceKey.create(EquipmentAssets.ROOT_ID, WingedSandals.id(NAME)),
    //?} else {
    /*WingedSandals.id(NAME),
    *///?}
  )
  //?}

  // From 1.20.5 to 1.21.1 armor materials live in a registry. Before 1.21.2
  // the sandals are repaired with gold ingots or another pair of sandals.
  //? if >=1.20.5 && <1.21.2 {
  /*private val gold = ArmorMaterials.GOLD.value()
  private val netherite = ArmorMaterials.NETHERITE.value()

  private val material = ArmorMaterial(
    gold.defense(),
    gold.enchantmentValue(),
    gold.equipSound(),
    { Ingredient.of(Items.GOLD_INGOT, ModItems.wingedSandals) },
    listOf(ArmorMaterial.Layer(WingedSandals.id(NAME))),
    gold.toughness(),
    netherite.knockbackResistance(),
  )

  val wingedSandalsMaterial: Holder<ArmorMaterial> =
    Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, WingedSandals.id(NAME), material)
  *///?}

  //? if <1.20.5 {
  /*val wingedSandalsMaterial: ArmorMaterial = WingedSandalsArmorMaterial
  *///?}
}

// Before 1.20.5 an armor material is an interface, and the game looks up its
// texture under this name in the minecraft namespace
//? if <1.20.5 {
/*private object WingedSandalsArmorMaterial : ArmorMaterial {
  private val gold = ArmorMaterials.GOLD
  private val netherite = ArmorMaterials.NETHERITE

  override fun getDurabilityForType(type: ArmorItem.Type): Int = gold.getDurabilityForType(type)
  override fun getDefenseForType(type: ArmorItem.Type): Int = gold.getDefenseForType(type)
  override fun getEnchantmentValue(): Int = gold.enchantmentValue
  override fun getEquipSound(): SoundEvent = gold.equipSound
  override fun getRepairIngredient(): Ingredient = Ingredient.of(Items.GOLD_INGOT, ModItems.wingedSandals)
  override fun getName(): String = "winged_sandals"
  override fun getToughness(): Float = gold.toughness
  override fun getKnockbackResistance(): Float = netherite.knockbackResistance
}
*///?}
