package party.lemons.seteffect.armor;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import party.lemons.seteffect.handler.GeneralHelper;

import java.util.Random;

/**
 * Created by Sam on 21/06/2018.
 */
public class ArmorEffectParticle implements IArmorEffect
{
	private final ParticleType type;
	private final float minx, miny, minz, maxx, maxy, maxz, minxoffset, minyoffset, minzoffset, maxxoffset, maxyoffset, maxzoffset, minspeed, maxspeed;
	private int amount;

	public ArmorEffectParticle(ParticleType type, float minx, float miny, float minz, float maxx, float maxy, float maxz, float minxoffset, float minyoffset, float minzoffset, float maxxoffset, float maxyoffset, float maxzoffset, float minspeed, float maxspeed, int amount)
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
	public void apply(LivingEntity player)
	{
		Random random = player.getRandom();
		World world = player.level;

		if(world.isClientSide){
			for(int i = 0; i < amount; i++)
			{
				float posX = GeneralHelper.randomRange(random, minx, maxx);
				float posY = GeneralHelper.randomRange(random, miny, maxy);
				float posZ = GeneralHelper.randomRange(random, minz, maxz);
				if (type == null){
					break;
				}
				float offsetX = GeneralHelper.randomRange(random, minxoffset, maxxoffset);
				float offsetY = GeneralHelper.randomRange(random, minyoffset, maxyoffset);
				float offsetZ = GeneralHelper.randomRange(random, minzoffset, maxzoffset);

				float speed = GeneralHelper.randomRange(random, minspeed, maxspeed);
				ClientWorld level = (ClientWorld) world;
				level.addParticle((IParticleData) type, player.position().x() + random.nextFloat() - 0.5f,
						player.position().y() + 0.5d + random.nextFloat() - 0.5f, player.position().z() + random.nextFloat() - 0.5f, 0, 0, 0);
			}

		}

	}
}
