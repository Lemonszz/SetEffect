package party.lemons.seteffect.armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

/**
 * Created by Sam on 22/10/2018.
 */
public class ArmorEffectImmune implements IArmorEffect
{
    private final Potion immunePotion;

    public ArmorEffectImmune(Potion potion)
    {
        this.immunePotion = potion;
    }

    @Override
    public void apply(EntityLivingBase living)
    {
        living.removePotionEffect(immunePotion);
    }
}
