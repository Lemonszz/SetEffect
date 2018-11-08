package party.lemons.seteffect.crt;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.mc1120.item.MCItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import party.lemons.seteffect.armor.ArmorEffectParticle;
import party.lemons.seteffect.armor.ArmorSet;
import party.lemons.seteffect.armor.ArmorSets;
import party.lemons.seteffect.handler.GeneralHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Created by Sam on 20/06/2018.
 */
@ZenRegister
@ZenClass("mods.SetEffect")
public class NewSet
{
	@ZenMethod
	public static ArmorSet newSet()
	{
		return new ArmorSet();
	}

	@ZenMethod
	public static ArmorSet getSetFromMaterial(String name)
	{
		ItemArmor.ArmorMaterial material = GeneralHelper.getArmorMaterialFromName(name);
		if(material == null)
			throw new NullPointerException("Armor material " + name + "doesn't exist :(");

		ArmorSet newSet = new ArmorSet().setName(name);
		for(Item it : ForgeRegistries.ITEMS.getValuesCollection())
		{
			if(it instanceof ItemArmor)
			{
				ItemArmor armor = (ItemArmor) it;
				if(armor.getArmorMaterial() == material)
				{
					ItemStack stack = new ItemStack(it);
					MCItemStack crtStack = new MCItemStack(stack);
					newSet.withPart(armor.armorType, crtStack);
				}
			}
		}

		return newSet;
	}

	@ZenMethod
	public static void register(ArmorSet set)
	{
		ArmorSets.addSet(set);
	}
}
