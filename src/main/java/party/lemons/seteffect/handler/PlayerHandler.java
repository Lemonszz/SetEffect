package party.lemons.seteffect.handler;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase != TickEvent.Phase.END  || event.player.level.isClientSide || event.player == null || !event.player.isAlive() || event.player.isSpectator())
			return;
		ticks++;
		if(ticks % 5 == 0)
		{
			ticks = 0;

			for(ArmorSet set : ArmorSets.sets)
			{
				if(set.isPlayerWearing(event.player))
				{

					set.applyEffects(event.player);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHurt(LivingAttackEvent event)
	{
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			for(ArmorSet set : ArmorSets.sets)
			{
				if(set.isPlayerWearing(player))
				{
					if(event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity)
						set.applyAttackerEffect((LivingEntity) event.getSource().getDirectEntity());
				}
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
