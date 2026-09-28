package com.adilhanney.wingedsandals

//? if >=26.1 {
import net.minecraft.resources.Identifier
//?} else {
/*import net.minecraft.resources.ResourceLocation
*///?}
import org.slf4j.LoggerFactory

/** Loader-independent mod constants. */
object WingedSandals {
  const val MOD_ID = "wingedsandals"
  val logger = LoggerFactory.getLogger(MOD_ID)!!

  /** @return the identifier of [path] in this mod's namespace */
  //? if >=26.1 {
  fun id(path: String): Identifier = Identifier.fromNamespaceAndPath(MOD_ID, path)
  //?} else if >=1.21 {
  /*fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
  *///?} else {
  /*fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
  *///?}
}
