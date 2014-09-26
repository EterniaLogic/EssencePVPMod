package com.example.examplemod.blocks;

import java.util.Random;

import com.example.examplemod.modnamex;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class HealBlock extends Block
{
        public HealBlock(int i, int j)
        {
                super(Material.rock);
                this.setBlockName("heal_block");
                this.setCreativeTab(modnamex.creativeTab);
                setBlockTextureName(modnamex.MODID + ":" + "heal_block");
        }

        public int idDropped(int i, Random random)
        {
                return 0;
        }
}
