package party.lemons.seteffect.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Sam on 20/06/2018.
 */
public class GeneralHelper
{
	public static PotionEffect clonePotionEffect(PotionEffect effect)
	{
		return new PotionEffect(effect);
	}

	@Nullable
	public static ItemArmor.ArmorMaterial getArmorMaterialFromName(String name)
	{
		for(ItemArmor.ArmorMaterial mat : ItemArmor.ArmorMaterial.values())
		{
			if(mat.name.equalsIgnoreCase(name))
				return mat;
		}

		return null;
	}

	public static void putStackInPlayerSlot(EntityEquipmentSlot slot, ItemStack stack, EntityPlayer player)
	{
		if (slot == EntityEquipmentSlot.MAINHAND)
		{
			player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
		}
		else if (slot == EntityEquipmentSlot.OFFHAND)
		{
			player.inventory.offHandInventory.set(0, stack);
		}
		else
		{
			player.inventory.armorInventory.set(slot.getIndex(), stack);
		}
	}

	public static float randomRange(Random random, float min, float max)
	{
		float val = min + random.nextFloat() * (max - min);
		return val;
	}
}
