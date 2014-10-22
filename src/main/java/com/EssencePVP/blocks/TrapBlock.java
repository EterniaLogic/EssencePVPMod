package com.EssencePVP.blocks;

import java.util.Random;

import org.junit.Test;

import com.EssencePVP.EssencePVP;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TrapBlock extends BlockContainer
{
        public TrapBlock(int i, int j)
        {
                super(Material.rock);
                this.setBlockName("trap_block");
                this.setCreativeTab(EssencePVP.getCreativeTab());
                //setBlockTextureName(modnamex.MODID + ":" + "textures/blocks/trap_block.jpg");
        }
        
        //TODO: make this block damage players based on GUI
        public int idDropped(int i, Random random)
        {
                return 0;
        }

		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			return new TrapTileEntity(this);
		}
}
