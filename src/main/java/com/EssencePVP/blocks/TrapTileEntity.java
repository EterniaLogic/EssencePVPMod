package com.EssencePVP.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TrapTileEntity extends TileEntity {
	int delayTimer=0, hits=0;
	BlockContainer block;
	public TrapTileEntity(BlockContainer blockc){
		super();
		this.block=blockc;
	}
	
	
	public void updateEntity()
	{
		// DelayTimer delays explosion by 0.5 seconds, prevents insta-death, prevents lag
		if(delayTimer >= 11){
			delayTimer = 0;
			// Get minecraft player who is standing directly on top of this block
			EntityPlayer player = worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 1.5D, (double)this.zCoord + 0.5D, 1.5);
			if(player != null){
				// kill
				hits++;
				player.setHealth(player.getHealth()-5); // damage player!
				float f = 2.0F;
	        	this.worldObj.createExplosion(player, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, f, true);
			}else if(hits >= 6){
				// Destroy this block!
				float f = 2.0F;
	        	this.worldObj.createExplosion(player, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, f, true);
			}
		}
		delayTimer++;
	}
}
