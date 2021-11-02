package party.lemons.seteffect.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;

/**
 * Created by Sam on 22/10/2018.
 */
public class ArmorEffectImmune implements IArmorEffect
{
    Effect immuneEffect;
    public ArmorEffectImmune(Effect effect)
    {
        this.immuneEffect = effect;
    }

    @Override
    public void apply(LivingEntity living)
    {
        if (!living.level.isClientSide)living.removeEffect(immuneEffect);
    }
}
