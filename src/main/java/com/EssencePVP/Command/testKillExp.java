package com.EssencePVP.Command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class testKillExp extends CommandBase{

	@Override
	public String getCommandName()
	{
		return "test";
		// Name of the command "test" will be called by "/test"
	}
	
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "Displays a test message";
		// Message to show when the user uses "/help test"
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
	     // Still empty for now
	}

}