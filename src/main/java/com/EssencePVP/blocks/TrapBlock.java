package com.EssencePVP.blocks;

import java.util.Random;

import org.junit.Test;

import com.EssencePVP.EssencePVP;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class TrapBlock extends Block
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
        
        @Test
        public void test(){
        	
        }
}
