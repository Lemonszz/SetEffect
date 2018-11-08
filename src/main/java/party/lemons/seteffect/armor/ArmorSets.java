package party.lemons.seteffect.armor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 20/06/2018.
 */
public class ArmorSets
{
	public static List<ArmorSet> sets = new ArrayList<>();

	public static void addSet(ArmorSet set)
	{
		sets.add(set);
	}

	public static ArmorSet getSetFromName(String name)
	{
		for(ArmorSet set : sets)
		{
			if(set.getName().equalsIgnoreCase(name))
				return set;
		}

		return null;
	}
}