package party.lemons.seteffect.handler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Sam on 20/06/2018.
 */
public class GeneralHelper
{

	public static float randomRange(Random random, float min, float max)
	{
		float val = min + random.nextFloat() * (max - min);
		return val;
	}


}
