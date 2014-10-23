package com.EssencePVP.blocks;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.*;

public class HealTileEntity extends TileEntity {
	int delayTimer=0;
	public void updateEntity()
	{
		// DelayTimer delays heals by 0.5 seconds, prevents insta-heal 
		if(delayTimer >= 11){
			delayTimer = 0;
			// Get minecraft player from the current world for this block
			EntityPlayer player = worldObj.getClosestVulnerablePlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, 5);
			if(player != null){
				player.heal(1);
			}
		}
		delayTimer++;
	}
}
