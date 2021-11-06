package party.lemons.seteffect.handler;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import party.lemons.seteffect.armor.ArmorSet;
import party.lemons.seteffect.armor.ArmorSets;

import java.util.List;

/**
 * Created by Sam on 20/06/2018.
 */
public class PlayerHandler
{
	static int ticks = 0;


	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingUpdateEvent event)
	{
		if(event.getEntityLiving().level.isClientSide || event.getEntityLiving() == null || !event.getEntityLiving().isAlive())
			return;
		ticks++;
		if(ticks % 100 == 0)
		{
			ticks = 0;

			for(ArmorSet set : ArmorSets.sets)
			{
				if(set.isPlayerWearing(event.getEntityLiving()))
				{

					set.applyEffects(event.getEntityLiving());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingAttackEvent event)
	{
			for(ArmorSet set : ArmorSets.sets) {
				if(set.isPlayerWearing(event.getEntityLiving()))
				{
					if(event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity)
						set.applyAttackerEffect((LivingEntity) event.getSource().getDirectEntity());
				}
			}
	}

	public static boolean hasGamestage(PlayerEntity player, List<String> gameStages)
	{
		if(ModList.get().isLoaded("gamestages") && !gameStages.isEmpty())
		{
			return GameStageHelper.hasAllOf(player, gameStages);
		}
		return true;
	}
}
