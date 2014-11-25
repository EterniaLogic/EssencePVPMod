package com.EssencePVP.gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.EssencePVP.EssencePVP;
import com.EssencePVP.Professions.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//TODO: make class skills configurable

//public class SkillGui extends GuiScreen
public class SkillGui extends GuiScreen
{
	Professions professions;
	public SkillGui(EntityPlayer player)
	{
		super();
		professions = EssencePVP.getInstance().getProfessions();
		initGui();
	}
	
	public final int xSizeOfTexture = 350;
	public final int ySizeOfTexture = 200;
	public FontRenderer frender = Minecraft.getMinecraft().fontRenderer;
	public int posX=0, posY=0;
	int state=0;
	private LinkedList controlList = new LinkedList();
	private int[] xList = {-10,-30,10,-30,-60,10,40}; // Raw X offsets for images
	private int[] yList = {40, 77, 77, 115, 115, 115, 115}; // raw Y offsets for images
	// names of the skills (hardcoded), can be changed later on
	/*private String[] meleeNames = {"RockFist","Sword Slice","Death Spin","Rigid Cleave","Backslash","Insta-Counter",""};
	private String[] rangeNames = {"Accuracy","Burst Shot","","","","",""};
	private String[] magicNames = {"Life Ball","Poison","Wind","Fireball","Wave","Lightning Shock",""};
	private String[] techyNames = {"Power Station","Mini Turret","Trap Mine","Mega Turret","","Void Trap","Nano Robots"};
	private String[] passvNames = {"Dig","Mine","Health","Sharpness","Quickness","Protection","Flight"};
	
	private String[] meleeDesc = {"Make punching more hard","Heavy sword strike that enemies cant fear","Attacks all enemies around with little interest","","","",""};
	private String[] rangeDesc = {"Aim-helping?","Multi-shot attack that might slam your friends","","","","",""};
	private String[] magicDesc = {"Because having two cubes isnt enough","So you had a bad burrito...","Wind","More fire. Can has?","Wave","",""};
	private String[] techyDesc = {"","","","","","","Nano Robots"};
	private String[] passvDesc = {"The ground needs you","You couldn't get enough of the ground","Health","Your mind is not sharp enough. But your muscles are.","NOT ENOUGH CAFFEINE!","Because creepers hate you","Land is overrated"};
	
	private String[] treeName = {"Melee","Ranged","Magic","Technical","Passive"};
	private String[] treeAddr = {"melee","ranged","magic","technical","passive"};*/
	
	int mouseAboveId=-1; // useful for detecting the current skill that the mouse is over
	
	@Override
	@SideOnly(Side.CLIENT)
	public void drawScreen(int mouseX, int mouseY, float f)
	{
		drawDefaultBackground();
		ResourceLocation imgloc = new ResourceLocation(EssencePVP.MODID+":textures/skillgui.png");
		//ITextureObject var4 = this.mc.renderEngine.getTexture(imgloc);
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(imgloc);

		posX = (this.width - xSizeOfTexture) / 2;
		posY = (this.height - ySizeOfTexture) / 2;
		drawTexturedModalRectNoUV(posX, posY, xSizeOfTexture, ySizeOfTexture);
		
		drawContent(state);
		drawMouseHint(mouseX, mouseY);

		super.drawScreen(mouseX, mouseY, f);
	}
	
	@SideOnly(Side.CLIENT)
	public void drawMouseHint(int mouseX, int mouseY){
	  int k=posX+xSizeOfTexture/2; 	// +xList[i];
	  int l=posY; 			// +yList[i];
	  mouseAboveId=-1;
	  // for loop to detect mouse location!
	  for(int i=0;i<7;i++){
	    // detect if mouse is in this area
	    if(mouseX < (k+xList[i]+20) && mouseY < (l+yList[i]+20)){
	      if(mouseX > (k+xList[i]) && mouseY > (l+yList[i])){
		List list = new ArrayList();
		mouseAboveId=i;
		
		// list of names select
		/*switch(state){
		  case 0:
		    list.add(meleeNames[i]);
		    list.add(meleeDesc[i]);
		    break;
		  case 1:
		    list.add(rangeNames[i]);
		    list.add(rangeDesc[i]);
		    break;
		  case 2:
		    list.add(magicNames[i]);
		    list.add(magicDesc[i]);
		    break;
		  case 3:
		    list.add(techyNames[i]);
		    list.add(techyDesc[i]);
		    break;
		  case 4:
		    list.add(passvNames[i]);
		    list.add(passvDesc[i]);
		    break;
		}*/
		
		Profession profession = professions.getProfession(state+1);
		Abilities abilities = profession.getAbilities();
		Ability ability = abilities.getAbility(i+1);
		list.add(ability.getAbilityName());
		list.add(profession.getProfessionDescription());
		
		this.drawHoveringText(list, (int)mouseX, (int)mouseY, frender);
	      }
	    }
	  }
	}
	
	public void drawContent(int idState){
		// draws tables of magic, ect
		FontRenderer frender = Minecraft.getMinecraft().fontRenderer;
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		Profession profession = professions.getProfession(state+1);
		Abilities abilities = profession.getAbilities();
		
		// state determines what list is used.
		frender.drawString(profession.getProfessionName()+" tree", posX+2, posY+40, 10);
		// loop through table
		for(int i=0;i<abilities.getAbilitiesCount();i++){
		  char[] iText = Character.toChars(i+65); // A-G names on the file
		  Ability ability = abilities.getAbility(i+1);
		  // Draw every item from the table
		  
		  // detect mouse over
		  if(mouseAboveId == i){
			/*GL11.glBegin(GL11.GL_QUADS);
			
			drawLoc(EssencePVP.MODID+":textures/"+treeAddr[state]+iText[0]+".png",posX+xSizeOfTexture/2+xList[i]+5, posY+yList[i]+5, 15, 15,false);
		    
		    GL11.glEnd();*/
			GL11.glColor4f(0.8f,0.8f,0.8f,1f);
			//drawLoc(EssencePVP.MODID+":textures/shadedskill.png",posX+xSizeOfTexture/2+xList[i], posY+yList[i], 20, 20,false);
			drawLoc(EssencePVP.MODID+":textures/"+ability.getAbilityIcon()+".png",posX+xSizeOfTexture/2+xList[i], posY+yList[i], 20, 20,true);
			GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
		  }else{
			  
		    drawLoc(EssencePVP.MODID+":textures/"+profession.getProfessionIcon()+".png",posX+xSizeOfTexture/2+xList[i], posY+yList[i], 20, 20,true);
		  }
		  
		}
	}
	
	public void initGui()
	{
		this.controlList.clear();
	
		int posx = (this.width - xSizeOfTexture) / 2;
		int posy = (this.height - ySizeOfTexture) / 2;
		int loffset1 = 3;
		int offset = 7;
		for(int i=0;i<professions.getProfessionCount();i++){
			Logger.global.log(Level.ALL, "AAAA: "+i+" asdf\n");
			Profession prof = professions.getProfession(i+1);
			int offset2 = 6*prof.getProfessionName().length();
			this.buttonList.add(new GuiButton(0, posx+loffset1+offset2, posy+10, 30, 12, prof.getProfessionName()));
		}
		/*this.buttonList.add(new GuiButton(0, posx+loffset1, posy+10, 30, 12, "Melee"));
		this.buttonList.add(new GuiButton(1, posx+loffset1+offset*4+2, posy+10, 40, 12, "Ranged"));		// 7*4+2	=	30	=>	30-0	=	30	6
		this.buttonList.add(new GuiButton(2, posx+loffset1+offset*10, posy+10, 30, 12, "Magic"));		// 7*10		=	70	=>	70-30	=	40	5
		this.buttonList.add(new GuiButton(3, posx+loffset1+offset*14+2, posy+10, 55, 12, "Technical")); // 7*14+2	=	100	=>	100-70	=	30	9
		this.buttonList.add(new GuiButton(4, posx+loffset1+offset*22, posy+10, 45, 12, "Passive"));*/	// 7*22		=	154	=>	154-100	=	54	7
		//GL11.glScaled(0.7f, 0.7f, 0.7f); // scale the button gui
	}
	
	// Button actions
	public void actionPerformed(GuiButton button)
	{
		if(button.id < professions.getProfessionCount()){
			state=button.id;
		}else{
			switch(button.id)
			{
			default:
				break;
			}
		}
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
