package com.EssencePVP.Command;

import com.EssencePVP.Player.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
		Minecraft mc = Minecraft.getMinecraft();
		
        if (mc != null && mc.objectMouseOver != null) {
        	Player player = (Player) Player.getPlayerMap().get(icommandsender.getCommandSenderName());
        	
        	if(player != null){
        		World world = player.getMcplayer().worldObj;
        		Vec3 lookat = player.getMcplayer().getLookVec();
        		
        		EntityLightningBolt lightningbolt = new EntityLightningBolt(world, lookat.xCoord, 128, lookat.yCoord);

	            world.spawnEntityInWorld(lightningbolt);
        	}
        }
	}
}
