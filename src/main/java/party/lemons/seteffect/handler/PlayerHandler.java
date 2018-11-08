package party.lemons.seteffect.handler;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import party.lemons.seteffect.SetEffect;
import party.lemons.seteffect.armor.ArmorSet;
import party.lemons.seteffect.armor.ArmorSets;

import java.util.List;

/**
 * Created by Sam on 20/06/2018.
 */
@Mod.EventBusSubscriber(modid = SetEffect.MODID)
public class PlayerHandler
{
	static int ticks = 0;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase != TickEvent.Phase.END || event.player.world.isRemote || event.player == null || event.player.isDead || event.player.isSpectator())
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
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			for(ArmorSet set : ArmorSets.sets)
			{
				if(set.isPlayerWearing(player))
				{
					if(event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityLivingBase)
						set.applyAttackerEffect((EntityLivingBase) event.getSource().getTrueSource());
				}
			}
		}
	}

	public static boolean hasGamestage(EntityPlayer player, List<String> gameStages)
	{
		if(Loader.isModLoaded("gamestages") && !gameStages.isEmpty())
		{
			return GameStageHelper.hasAllOf(player, gameStages);
		}
		return true;
	}
}
