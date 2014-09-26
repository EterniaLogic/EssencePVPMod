package com.example.examplemod.blocks;

import java.util.Random;

import com.example.examplemod.modnamex;
import com.example.examplemod.gui.SkillGui;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


// this block levels player very very slowly while they stand on it.
// Requires many diamonds in the recipe/
public class LevelBlock extends Block
{
        public LevelBlock(int i, int j)
        {
                super(Material.rock);
                this.setBlockName("level_block");
                this.setCreativeTab(modnamex.creativeTab);
        }

        public int idDropped(int i, Random random)
        {
                return 0;
        }
        
        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
        {
        player.openGui(modnamex.instance, 0, world, x, y, z);
        //player.open
        //new SkillGui(player);
        //return true;
		return true;
        }
}
