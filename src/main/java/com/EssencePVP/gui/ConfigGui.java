package com.EssencePVP.gui;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.EssencePVP.ClientThread;
import com.EssencePVP.EssencePVP;
import com.EssencePVP.Professions.Professions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class ConfigGui extends GuiScreen {
	public ConfigGui(){
		super();
		initGui();
		scrollbar = new ScrollBarGui();
		professions=EssencePVP.getInstance().getProfessions();
		
		// Register this gui with the client thread so that it can tick animations, scrollbars, ect.
		ClientThread.getInstance().getTickList().add(new Runnable(){
			@Override
			public void run() {
				animate();
			}
		});
	}
	
	public final int xSizeOfTexture = 350;
	public final int ySizeOfTexture = 200;
	public FontRenderer frender = Minecraft.getMinecraft().fontRenderer;
	public int posX=0, posY=0;
	int state=0, state2=0; // State1 handles tabs, State2 is for an advanced substate
	private LinkedList controlList = new LinkedList();
	int mouseAboveId=-1; // useful for detecting the current skill that the mouse is over
	private String[] configName = {"Configure Abilities","Configure Professions","Configure Factions"};
	private Professions professions;
	private ScrollBarGui scrollbar;
	
	// Animation
	float mixspeed=1.0f; // Random mixing speed to give appeal
	int mixOffsetX=0, mixOffsetY=0, mixTgtX=0,mixTgtY=0,mixSpdX=0, mixSpdY=0;
	int scrollOffsetX=0; // Scroll Bar offset
	
	@Override
	@SideOnly(Side.CLIENT)
	public void drawScreen(int mouseX, int mouseY, float f)
	{
		drawDefaultBackground();
		ResourceLocation imgloc = new ResourceLocation(EssencePVP.MODID+":textures/configgui.png");
		//ITextureObject var4 = this.mc.renderEngine.getTexture(imgloc);
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(imgloc);

		posX = (this.width - xSizeOfTexture) / 2;
		posY = (this.height - ySizeOfTexture) / 2;
		drawTexturedModalRectNoUV(posX, posY, xSizeOfTexture, ySizeOfTexture);
		
		drawContent(state);
		//drawMouseHint(mouseX, mouseY);

		super.drawScreen(mouseX, mouseY, f);
	}
	
	public void initGui()
	{
		this.controlList.clear();
	
		int posx = (this.width - xSizeOfTexture) / 2;
		int posy = (this.height - ySizeOfTexture) / 2;
		int loffset1 = 3;
		int offset = 7;
		this.buttonList.add(new GuiButton(0, posx+loffset1, posy+10, 30, 12, "Abilities"));
		this.buttonList.add(new GuiButton(1, posx+loffset1+offset*4+2, posy+10, 40, 12, "Professions"));
		this.buttonList.add(new GuiButton(2, posx+loffset1+offset*10, posy+10, 30, 12, "Factions"));
		//GL11.glScaled(0.7f, 0.7f, 0.7f); // scale the button gui
	}
	
	public void drawContent(int idState){
		// draws tables of magic, ect
		FontRenderer frender = Minecraft.getMinecraft().fontRenderer;
		posX = (this.width - xSizeOfTexture) / 2;
		posY = (this.height - ySizeOfTexture) / 2;
		
		// state determines what list is used.
		frender.drawString(configName[state], posX+xSizeOfTexture/2-configName[state].length()*5, posY+40, 10);
		// loop through table
		for(int i=0;i<7;i++){
		  char[] iText = Character.toChars(i+65); // A-G names on the file
		  // Draw every item from the table
		  
		  // detect mouse over
		  if(mouseAboveId == i){
			
		  }else{
			  switch(state){
			  case 0:
				  drawProfPage();
				  break;
			  }
		  }
		}
	}
	
		// Button actions
	public void actionPerformed(GuiButton button)
	{
		if(button.id < 5){
			state=button.id;
		}else{
			switch(button.id)
			{
			default:
				break;
			}
		}
	}
	
	public void drawProfPage(){
		switch(state2){
		case 0:
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			//drawTexturedModalRectNoUV(posX+10,posY);
			// Loop through professions
			for(int i=0;i<professions.getProfessionCount();i++){
				professions.getProfession(i).getProfessionName();
				professions.getProfession(i).getProfessionDescription();
			}
			
			GL11.glEnd();
			
			
			// Draw 
			scrollbar.drawScrollBar();
			break;
		}
		
	}
	
	// Called from Client thread
	public void animate(){
		
	}
		
	@SideOnly(Side.CLIENT)
	public void drawLoc(String loc, int x, int y, int width, int height, boolean resetcolor){
		if(resetcolor) GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ResourceLocation imgloc = new ResourceLocation(loc);
		this.mc.renderEngine.bindTexture(imgloc);
		drawTexturedModalRectNoUV(x,y,width,height);
	}
	
	// Modified excerpt from Gui.class
	@SideOnly(Side.CLIENT)
	 public void drawTexturedModalRectNoUV(int x, int y, int width, int height)
	 {
	     Tessellator tessellator = Tessellator.instance;
	     tessellator.startDrawingQuads();    
	     tessellator.addVertexWithUV(x        , y + height, 0, 0.0, 1.0);
	     tessellator.addVertexWithUV(x + width, y + height, 0, 1.0, 1.0);
	     tessellator.addVertexWithUV(x + width, y         , 0, 1.0, 0.0);
	     tessellator.addVertexWithUV(x        , y         , 0, 0.0, 0.0);
	     tessellator.draw();
	 }
}
