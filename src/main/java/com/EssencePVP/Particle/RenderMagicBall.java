package com.EssencePVP.Particle;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMagicBall extends Render {

	private static final ResourceLocation ballTextures = new ResourceLocation("textures/entity/arrow.png");
    private static final String __OBFID = "EssencePVP_01";

	
	@Override
	@SideOnly(Side.CLIENT)
	public void doRender(Entity p_76986_1_, double p_76986_2_,
			double p_76986_4_, double p_76986_6_, float p_76986_8_,
			float p_76986_9_) {
		// force into 2D Orthogonal mode
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0,0,64,64, -1, 1); // Orthogonal setting

		GL11.glMatrixMode(GL11.GL_MODELVIEW); // new matrix with translation
		GL11.glLoadIdentity();
		GL11.glTranslated(0.375, 0.375, 0.0);

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		
		// Start drawing particle box
		this.bindEntityTexture(p_76986_1_);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Tessellator tessellator = Tessellator.instance;
		
		// move near player
		GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
		
		// Draw a particle box
		tessellator.startDrawingQuads();
		tessellator.addVertex(-1, -1, 0); // X, Y, Z
		tessellator.addVertex(-1, 1, 0); // X, Y, Z
		tessellator.addVertex(1, -1, 0); // X, Y, Z
		tessellator.addVertex(1, 1, 0); // X, Y, Z
		tessellator.draw();
		
		// end of draw
		GL11.glDisable(GL12.GL_RESCALE_NORMAL); 
        GL11.glPopMatrix();
        
        
        // force into 3D mode again
        GL11.glViewport(0, 0, 64, 64);
        GL11.glMatrixMode(GL11.GL_PROJECTION);

        GL11.glLoadIdentity();
        GLU.gluPerspective(45f, 1f, 0.1f, 5000.0f);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11. glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return ballTextures;
	}

}
