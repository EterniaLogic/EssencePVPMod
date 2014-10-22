package com.EssencePVP.blocks;

import java.util.Random;

import com.EssencePVP.EssencePVP;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class HealBlock extends BlockContainer
{
        public HealBlock(int i, int j)
        {
                super(Material.rock);
                this.setBlockName("heal_block");
                this.setCreativeTab(EssencePVP.getCreativeTab());
                setBlockTextureName(EssencePVP.MODID + ":" + "heal_block");
        }
        
        // TODO: Heal a player within a distance

        public int idDropped(int i, Random random)
        {
                return 0;
        }

		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			// TODO Auto-generated method stub
			return new HealTileEntity();
		}
}
