package party.lemons.seteffect.handler;

import java.util.Random;

/**
 * Created by Sam on 20/06/2018.
 */
public class GeneralHelper
{

	public static float randomRange(Random random, float min, float max)
	{
		float val = min + random.nextFloat() * (max - min);
		return val;
	}


}
