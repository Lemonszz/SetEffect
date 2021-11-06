package party.lemons.seteffect.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class ArmorEffectAttributes implements IArmorEffect{
    private final Attribute attr;
    private final AttributeModifier modifier;
    public ArmorEffectAttributes(Attribute attribute, AttributeModifier modifier){
        this.attr = attribute;
        this.modifier = modifier;

    }
    @Override
    public void apply(LivingEntity living) {
        living.getArmorSlots().forEach(thing -> {thing.addAttributeModifier(attr, modifier, thing.getEquipmentSlot());});
    }
}
