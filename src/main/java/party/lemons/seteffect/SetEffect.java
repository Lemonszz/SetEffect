package party.lemons.seteffect;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import party.lemons.seteffect.handler.PlayerHandler;

/**
 * Created by Sam on 20/06/2018.
 */
@Mod(SetEffect.MODID)
public class SetEffect
{
	public static final String MODID = "seteffect";
	public static final String NAME = "Set Effect";
	public static final String VERSION = "1.0.0";

	public SetEffect(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}
	private void setup(final FMLCommonSetupEvent event){
		MinecraftForge.EVENT_BUS.register(PlayerHandler.class);
	}
}
