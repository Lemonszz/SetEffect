package party.lemons.seteffect;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import party.lemons.seteffect.command.CommandSetEffect;

/**
 * Created by Sam on 20/06/2018.
 */
@Mod(modid = SetEffect.MODID, name = SetEffect.NAME, version = SetEffect.VERSION, dependencies = SetEffect.DEPS)
public class SetEffect
{
	public static final String MODID = "seteffect";
	public static final String NAME = "Set Effect";
	public static final String VERSION = "2.4.0";
	public static final String DEPS = "required-after:crafttweaker;after:gamestages";

	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSetEffect());
	}
}
