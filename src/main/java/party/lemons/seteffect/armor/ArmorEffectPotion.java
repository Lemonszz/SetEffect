package party.lemons.seteffect.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
/**
 * Created by Sam on 21/06/2018.
 */
public class ArmorEffectPotion implements IArmorEffect {
	private final EffectInstance effectInstance;

	public ArmorEffectPotion(EffectInstance instance){

		this.effectInstance = instance;
	}

	@Override
	public void apply(LivingEntity player)
	{
		if (!player.level.isClientSide)
		player.addEffect(effectInstance);
	}
}
