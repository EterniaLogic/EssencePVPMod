package com.EssencePVP.Command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class LightningCommand extends CommandBase{

	@Override
	public String getCommandName()
	{
		return "lightning";
		// Name of the command "test" will be called by "/test"
	}
	
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "Strikes a foe where you are looking";
		// Message to show when the user uses "/help test"
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
	     // Still empty for now
	}
}
