package party.lemons.seteffect.armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import party.lemons.seteffect.handler.GeneralHelper;

/**
 * Created by Sam on 21/06/2018.
 */
public class ArmorEffectPotion implements IArmorEffect
{
	private final PotionEffect effect;

	public ArmorEffectPotion(PotionEffect effect)
	{
		this.effect = effect;
	}

	@Override
	public void apply(EntityLivingBase player)
	{
		player.addPotionEffect(GeneralHelper.clonePotionEffect(effect));
	}
}
