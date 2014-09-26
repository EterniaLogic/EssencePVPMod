package com.EssencePVP.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

// Client network tutorial:
// 	http://www.minecraftforum.net/forums/archive/tutorials/931088-1-4-7-forge-blaueseichoerns-gui-tutorial
public class CommonProxy implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		/*TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (tileEntity != null) {
			switch (ID) {
			case 0: 
			}
		}*/
		return null;
	}
}
