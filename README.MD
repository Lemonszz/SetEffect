1.16 port of SetEffects

Includes most methods from the original mod:

Package: `import mods.SetEffect.ArmorSet`

Static Methods:

`ArmorSet.newSet as ArmorSet` -> Creates a new ArmorSet

`ArmorSet.dumpParticleNames() as void` -> Prints a list of particle names to the crafttweaker.log file.

Instance Methods: 

`MyArmorSet.setName(name as string) as ArmorSet` -> Sets the name of the ArmorSet

`MyArmorSet.inSlot(slot as MCEquipmentSlotType, item as IItemStack) as ArmorSet` -> Checks for a certain IItemStack in the specified [slot](https://docs.blamejared.com/1.16/en/vanilla/api/util/MCEquipmentSlotType)

`MyArmorSet.setIgnoreNBT() as ArmorSet` -> Ignores NBT data for the specified Items in the ArmorSet

`MyArmorSet.requireGamestages(stages as string[]) as ArmorSet` -> Adds the specified GameStages to the required GameStages list. Requires GameStagesAPI to be installed.

`MyArmorSet.setStrict() as ArmorSet` -> Sets the comparing type of the 

`MyArmorSet.addParticle(particleName as string, minx as float, miny as float, minz as float, maxx as float, maxy as float,  maxz as float,  minxoffset as float,  minyoffset as float,minzoffset as float, float maxxoffset,  maxyoffset as float,  maxzoffset as float,  minspeed as float,  maxspeed as float,  amount as int) as ArmorSet` -> Adds a particle that appears when the player is wearing the full set.

`MyArmorSet.addImmunity(effect as MCPotionEffectInstance) as ArmorSet` -> Makes the player wearing the set immune to that effect.

`MyArmorSet.addEffect(effect as MCPotionEffectInstance) as ArmorSet` -> Makes the player wearing the set receive that effect.

`MyArmorSet.addAttackerEffect(effect as MCPotionEffectInstance) as ArmorSet` -> Makes the entity that attacks the player wearing this set receive an effect.

`MyArmorSet.register() as void  ` -> Registers our ArmorSet.



Example Set:
`````
import mods.SetEffect.ArmorSet;

ArmorSet.dumpParticleNames(); //Can be commented out after getting the right name for what you want.

val iron_set = new ArmorSet()
               .setName("example_set")
               .inSlot(<equipmentslottype:head>, <item:minecraft:iron_helmet>)
               .inSlot(<equipmentslottype:chest>, <item:minecraft:iron_chestplate>)
               .inSlot(<equipmentslottype:legs>, <item:minecraft:iron_leggings>)
               .inSlot(<equipmentslottype:feet>, <item:minecraft:iron_boots>)
               .setIgnoreNBT()
               .addEffect(<effect:minecraft:regeneration>.newInstance(100, 5))
               .addImmunity(<effect:minecraft:blindness>)
               .addAttackerEffect(<effect:minecraft:poison>.newInstance(60, 1))
               .requireGamestages("hello");
iron_set.register();

//You can also chain the register to remove the need for a variable.

 new ArmorSet().setName("iron_set")
                .inSlot(<equipmentslottype:head>, <item:minecraft:iron_helmet>)
               .inSlot(<equipmentslottype:chest>, <item:minecraft:iron_chestplate>)
               .inSlot(<equipmentslottype:legs>, <item:minecraft:iron_leggings>)
               .inSlot(<equipmentslottype:feet>, <item:minecraft:iron_boots>)
               .addEffect(<effect:minecraft:regeneration>.newInstance(10, 5))
               .addParticle("minecraft:angry_villager",  1.0f, 1.0f, 1.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, -5.0f, -5.0f, -5.0f, 2.0f, 0.3f, 2);
               .register();

`````