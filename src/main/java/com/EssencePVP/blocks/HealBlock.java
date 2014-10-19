package com.EssencePVP.blocks;

import java.util.Random;

import com.EssencePVP.EssencePVP;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class HealBlock extends Block
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
}
