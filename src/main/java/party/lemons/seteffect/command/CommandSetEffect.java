package party.lemons.seteffect.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import party.lemons.seteffect.armor.ArmorSet;
import party.lemons.seteffect.armor.ArmorSets;
import party.lemons.seteffect.handler.GeneralHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sam on 21/06/2018.
 */
public class CommandSetEffect extends CommandBase
{
	@Override
	public String getName()
	{
		return "seteffect";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/seteffect [option]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(args.length < 1 || !(sender instanceof EntityPlayer))
		{
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}

		String option = args[0];
		switch(option)
		{
			case "find":
				sender.sendMessage(new TextComponentString(getItemStackArmor((EntityPlayer) sender)));
			break;
			case "give":
				if(args.length < 2)
				{
					sender.sendMessage(new TextComponentString("/seteffect give [set_name]"));
					return;
				}

				String name = args[1];
				ArmorSet set = ArmorSets.getSetFromName(name);
				if(set != null)
				{
					giveSet((EntityPlayer) sender, set);
				}
				else
				{
					sender.sendMessage(new TextComponentString("That set doesn't exist."));
				}

				break;
		}
	}

	private void giveSet(EntityPlayer sender, ArmorSet set)
	{
		for(EntityEquipmentSlot slot : set.getArmor().keySet())
		{
			GeneralHelper.putStackInPlayerSlot(slot, set.getArmor().get(slot).stream().findFirst().get(), sender);
		}
	}

	private String getItemStackArmor(EntityPlayer sender)
	{
		ItemStack held = sender.getHeldItemMainhand();
		if(!held.isEmpty() && held.getItem() instanceof ItemArmor)
		{
			ItemArmor.ArmorMaterial material = ((ItemArmor)held.getItem()).getArmorMaterial();
			return material.name;
		}

		return "This is not part of a valid armor set.";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
	{
		if(args.length == 1)
			return options;

		if(args.length == 2 && args[0].equalsIgnoreCase("give"))
		{
			if(setList == null)
			{
				setList = new ArrayList<>();
				for(ArmorSet set : ArmorSets.sets)
				{
					if(!set.getName().isEmpty())
					{
						setList.add(set.getName());
					}
				}
			}

			return setList;
		}

		return Collections.emptyList();
	}

	private static final List<String> options = new ArrayList<>();
	private static List<String> setList = null;
	static
	{
		options.add("find");
		options.add("give");
	}
}
