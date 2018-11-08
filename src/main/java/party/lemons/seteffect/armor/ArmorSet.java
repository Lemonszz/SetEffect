package party.lemons.seteffect.armor;

import com.google.common.collect.ArrayListMultimap;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotion;
import crafttweaker.api.potions.IPotionEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import party.lemons.seteffect.crt.NewSet;
import party.lemons.seteffect.handler.GeneralHelper;
import party.lemons.seteffect.handler.PlayerHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 20/06/2018.
 */
@ZenClass("seteffect.armor.ArmorSet")
public class ArmorSet
{
	private final Multimap<EntityEquipmentSlot, ItemStack> armor;

	private final List<IArmorEffect> effects;
	private final List<IArmorEffect> attackerEffects;

	private final List<String> requiredStages;
	private String name = "";
	private boolean strict, ignoreNBT;

	public ArmorSet()
	{
	    this.armor = ArrayListMultimap.create();
		//this.armor = new ArrayListMultimap<>();
		this.effects = new ArrayList<>();
		this.attackerEffects = new ArrayList<>();
		this.requiredStages = new ArrayList<>();
		this.strict = false;
		this.ignoreNBT = false;
	}

	@ZenMethod
	public ArmorSet requireGamestage(String stage)
	{
		requiredStages.add(stage);
		return this;
	}

	@ZenMethod
	public ArmorSet register()
	{
		NewSet.register(this);
		return this;
	}

	@ZenMethod
	public ArmorSet addParticle(String particleName, float minx, float miny, float minz, float maxx, float maxy, float maxz, float minxoffset, float minyoffset, float minzoffset, float maxxoffset, float maxyoffset, float maxzoffset, float minspeed, float maxspeed, int amount)
	{
		EnumParticleTypes type = EnumParticleTypes.getByName(particleName);
		if(type == null)
			throw new NullPointerException("No such particle " + particleName);

		ArmorEffectParticle eff = new ArmorEffectParticle(type, minx, miny, minz, maxx, maxy, maxz, minxoffset, minyoffset, minzoffset, maxxoffset, maxyoffset, maxzoffset, minspeed, maxspeed, amount);

		effects.add(eff);
		return this;
	}

	@ZenMethod
    public ArmorSet addImmunity(IPotion potion)
    {
        return addEffect(new ArmorEffectImmune(CraftTweakerMC.getPotion(potion)));
    }

	@ZenMethod
	public ArmorSet addEffect(IArmorEffect effect)
	{
		effects.add(effect);
		return this;
	}

	@ZenMethod
	public ArmorSet addEffect(IPotionEffect effect)
	{
		effects.add(new ArmorEffectPotion(CraftTweakerMC.getPotionEffect(effect)));
		return this;
	}

	@ZenMethod
	public ArmorSet addAttackerEffect(IPotionEffect effect)
	{
		attackerEffects.add(new ArmorEffectPotion(CraftTweakerMC.getPotionEffect(effect)));
		return this;
	}

	@ZenMethod
	public ArmorSet addAttackerEffect(IArmorEffect effect)
	{
		attackerEffects.add(effect);
		return this;
	}

	@ZenMethod
	public ArmorSet withHead(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.HEAD, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withChest(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.CHEST, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withLegs(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.LEGS, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withFeet(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.FEET, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withMainhand(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.MAINHAND, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withOffhand(IItemStack stack)
	{
		armor.put(EntityEquipmentSlot.OFFHAND, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet withPart(EntityEquipmentSlot slot, IItemStack stack)
	{
		armor.put(slot, CraftTweakerMC.getItemStack(stack));
		return this;
	}

	@ZenMethod
	public ArmorSet setName(String name)
	{
		this.name = name.replace(" ", "_");
		return this;
	}

	@ZenMethod
	public ArmorSet setStrict()
	{
		this.strict = true;
		return this;
	}

	@ZenMethod
    public ArmorSet setIgnoreNBT()
    {
        this.ignoreNBT = true;
        return this;
    }

	public boolean isPlayerWearing(EntityPlayer player)
	{
		return isPlayerWearing(player, strict);
	}

	private boolean isPlayerWearing(EntityPlayer player, boolean strict)
	{
		if(!PlayerHandler.hasGamestage(player, requiredStages))
			return false;

		for(EntityEquipmentSlot slot : armor.keySet())
		{
		    boolean match = false;

		    for(ItemStack stack : armor.get(slot))
            {
                ItemStack playerStack = player.getItemStackFromSlot(slot);
                match = itemMatch(playerStack, stack, strict);

                if(match)
                    break;
            }


			if(!match)
				return false;
		}

		return true;
	}

	private boolean itemMatch(ItemStack playerStack, ItemStack compareStack, boolean strict)
	{
		if(strict)
			return isItemStackEqualStrict(playerStack, compareStack);

		return isItemStackEqual(playerStack, compareStack);
	}

	private boolean isItemStackEqual(ItemStack playerStack, ItemStack compareStack)
	{
		if (playerStack.getCount() != playerStack.getCount())
		{
			return false;
		}
		else if (playerStack.getItem() != compareStack.getItem())
		{
			return false;
		}
		else if (playerStack.getTagCompound() == null && compareStack.getTagCompound() != null)
		{
			return false;
		}
		else
		{
			if((playerStack.getTagCompound() != null && compareStack != null) && !ignoreNBT)
			{
				NBTTagCompound playerTags = playerStack.getTagCompound();
				NBTTagCompound compareTags = compareStack.getTagCompound();

				if(compareTags == null || compareTags.getKeySet() == null)
					return false;

				for(String tag : compareTags.getKeySet())
				{
					if(playerTags.hasKey(tag))
					{
						NBTBase pTag = playerTags.getTag(tag);
						NBTBase cTag = compareTags.getTag(tag);

						if(!pTag.equals(cTag))
							return false;
					}
					else
					{
						return false;
					}
				}
				return true;
			}
			else
			{
				return true;
			}
		}
	}

	private boolean isItemStackEqualStrict(ItemStack playerStack, ItemStack compareStack)
	{
		if (playerStack.getCount() != playerStack.getCount())
		{
			return false;
		}
		else if (playerStack.getItem() != compareStack.getItem())
		{
			return false;
		}
		else if (playerStack.getTagCompound() == null && compareStack.getTagCompound() != null)
		{
			return false;
		}
		else
		{
			return (playerStack.getTagCompound() == null || playerStack.getTagCompound().equals(compareStack.getTagCompound())) && playerStack.areCapsCompatible(compareStack);
		}
	}

	public void print()
	{
		for(EntityEquipmentSlot slot : armor.keySet())
		{
			System.out.println(slot + " - " + armor.get(slot));
		}
	}

	public void applyEffects(EntityPlayer player)
	{
		effects.forEach(e -> e.apply(player));
	}

	public void applyAttackerEffect(EntityLivingBase livingBase)
	{
		attackerEffects.forEach(e -> e.apply(livingBase));
	}

	public Multimap<EntityEquipmentSlot, ItemStack> getArmor()
	{
		return armor;
	}

	public String getName()
	{
		return name;
	}
}
