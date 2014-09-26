package com.example.examplemod.gui;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//Client network tutorial:
//	http://www.minecraftforum.net/forums/archive/tutorials/931088-1-4-7-forge-blaueseichoerns-gui-tutorial
public class ClientProxy extends CommonProxy {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		Block b = world.getBlock(x,y,z);
		if (tileEntity != null) {
			switch (ID) {
			case 0: /* your GUIs go here */
				return new SkillGui(player);
			}
		}else if(b != null){
			switch (ID) {
			case 0: /* your GUIs go here */
				return new SkillGui(player);
			}
		}
		return tileEntity;

	}
}