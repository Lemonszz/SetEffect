package party.lemons.seteffect.armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import party.lemons.seteffect.handler.GeneralHelper;

import java.util.Random;

/**
 * Created by Sam on 21/06/2018.
 */
public class ArmorEffectParticle implements IArmorEffect
{
	private final EnumParticleTypes type;
	private final float minx, miny, minz, maxx, maxy, maxz, minxoffset, minyoffset, minzoffset, maxxoffset, maxyoffset, maxzoffset, minspeed, maxspeed;
	private int amount;

	public ArmorEffectParticle(EnumParticleTypes type, float minx, float miny, float minz, float maxx, float maxy, float maxz, float minxoffset, float minyoffset, float minzoffset, float maxxoffset, float maxyoffset, float maxzoffset, float minspeed, float maxspeed, int amount)
	{
		this.type = type;
		this.minx = minx;
		this.miny = miny;
		this.minz = minz;
		this.maxx = maxx;
		this.maxy = maxy;
		this.maxz = maxz;
		this.minxoffset = minxoffset;
		this.minyoffset = minyoffset;
		this.minzoffset = minzoffset;
		this.maxxoffset = maxxoffset;
		this.maxyoffset = maxyoffset;
		this.maxzoffset = maxzoffset;
		this.amount = amount;
		this.minspeed = minspeed;
		this.maxspeed = maxspeed;
	}

	@Override
	public void apply(EntityLivingBase player)
	{
		Random random = player.getRNG();
		World world = player.world;

		if(world.isRemote)
			return;

		for(int i = 0; i < amount; i++)
		{
			float posX = GeneralHelper.randomRange(random, minx, maxx);
			float posY = GeneralHelper.randomRange(random, miny, maxy);
			float posZ = GeneralHelper.randomRange(random, minz, maxz);

			float offsetX = GeneralHelper.randomRange(random, minxoffset, maxxoffset);
			float offsetY = GeneralHelper.randomRange(random, minyoffset, maxyoffset);
			float offsetZ = GeneralHelper.randomRange(random, minzoffset, maxzoffset);

			float speed = GeneralHelper.randomRange(random, minspeed, maxspeed);

			((WorldServer)world).spawnParticle(type, (player.posX + 0.5F) - posX, player.posY + posY, (player.posZ + 0.5F) - posZ, 1, offsetX, offsetY, offsetZ, speed);
		}
	}
}
