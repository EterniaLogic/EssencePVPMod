// This file is part of EssencePvP.

// EssencePvP is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// EssencePvP is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with EssencePvP.  If not, see <http://www.gnu.org/licenses/>.

package com.EssencePVP;

import java.io.File;
import java.io.IOException;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.block.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import com.EssencePVP.blocks.HealBlock;
import com.EssencePVP.blocks.LevelBlock;
import com.EssencePVP.blocks.TrapBlock;
import com.EssencePVP.gui.ClientProxy;
import com.EssencePVP.gui.CreativeTabsEMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = EssencePVP.MODID, version = EssencePVP.VERSION)
public class EssencePVP
{
    public static final String MODID = "essencepvp";
    public static final String VERSION = "0.1";
    
    public static Block hLevelBlock;
    public static Block hTrapBlock;
    public static Block hHealBlock;

    public static int healblockid=0, trapblockid=0, levelblockid=0;
    
    public static CreativeTabs creativeTab = new CreativeTabsEMod("EssencePVP");
    
    // handles GUI synchronization
    @SidedProxy(clientSide = "com.EssencePVP.gui.ClientProxy", serverSide = "com.EssencePVP.gui.CommonProxy")
    public static ClientProxy proxy;

    // allows for methods to access this mod's raw members
    @Instance("EssencePVP")
    public static EssencePVP instance;
    
    public Configuration config;
 
    // mySQL   
    public boolean bUseMySQL;
    public String bMySQLHostname;
    public int iMySQLPort;
    public String sMySQLUsername;
    public String sMySQLPassword;

	public double dExperienceRate;
	public float fFundsGainRate;
	public float fScoreGainRate;
	public boolean bGainExp;
    public Metricz metrics=null;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	instance = this;
    	//HelloWorld.doHello();
    	
    	// handles gui
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, this.proxy);
    	if(Minecraft.getMinecraft().getIntegratedServer() != null){
	    	if(Minecraft.getMinecraft().getIntegratedServer().isServerRunning()){
	    		try {
					metrics = new Metricz("EssencePVP",VERSION);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
	    	}
    	}
    	
    	// Generate or load the config
    	generateLoadConfig();
    	
    	// Load Blocks
    	hLevelBlock = (new LevelBlock(levelblockid, 1)).setHardness(1.5F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName(EssencePVP.MODID + ":" + "level_block");
    	hTrapBlock = (new TrapBlock(trapblockid, 1)).setHardness(1.5F).setResistance(25F).setStepSound(Block.soundTypeMetal).setBlockTextureName(EssencePVP.MODID + ":" + "trap_block");
    	hHealBlock = (new HealBlock(healblockid, 1)).setHardness(1.0F).setResistance(2F).setStepSound(Block.soundTypeWood).setBlockTextureName(EssencePVP.MODID + ":" + "heal_block");
    	
    	GameRegistry.registerBlock(hHealBlock, "heal_block");
    	GameRegistry.registerBlock(hTrapBlock, "trap_block");
    	GameRegistry.registerBlock(hLevelBlock, "level_block");
    	
    	// Items
    
    	// Scala direct call
    	// Scala manages scripting for the mod
    	
    	// Java manages mod items, blocks, animations and performance

        // Test
        // Professions pTest = new Professions();
        // pTest.addProfession("Healer", "This is a healer class test");
        // pTest.addProfession("Guardian", "This is a tank class test");
        // pTest.delProfession(1);
        // System.out.println(">>>>>>>>>>>>>>>"+pTest.getProfessionCount());
    }
    
    public void generateLoadConfig(){
    	config = new Configuration(new File("config/EssencePVP.cfg"),"0.1");
    	config.load();

    	// Mod configurations
    	dExperienceRate = config.getFloat("dExperienceRate","GameCFG",1.0f,0.1f,10000.0f,"Set the exp rate for the skill tree");
    	fFundsGainRate = config.getFloat("MoneyRate","GameCFG",1.0f,0.1f,10000.0f,"Sets the speed in which players get money from pvp");
    	fScoreGainRate = config.getFloat("ScoreRate","GameCFG",1.0f,0.1f,10000.0f,"Sets the speed in which players get score from pvp");
    	bGainExp= config.getBoolean("AccumulateExp","ServerCFG",true,"Will players gain exp from hitting other players? If not, they get exp from killing others");
    	
    	// Mysql Server stuffs
    	bUseMySQL=config.getBoolean("UseDatabaseServer","ServerCFG",false,"Do you want to use a database server? If not, SQLite will be used.");
    	bMySQLHostname=config.getString("bMySQLHostname","ServerCFG","","Mysql Server location");
    	iMySQLPort=config.getInt("iMySQLPort","ServerCFG",3306,1,65535,"Mysql Port");
    	sMySQLUsername=config.getString("sMySQLUsername","ServerCFG","user","Mysql Username");
    	sMySQLPassword=config.getString("MysqlPW","ServerCFG","pass","Mysql Password");
    	
    	// Block configurations
    	healblockid = config.getInt("healblockid", "BlockIDs", 3380, 512, 40000, "Heal Block ID");
    	trapblockid = config.getInt("trapblockid", "BlockIDs", 3381, 512, 40000, "Trap Block ID");
    	levelblockid= config.getInt("levelblockid","BlockIDs", 3382, 512, 40000, "Level Block ID");
    	
    	config.save(); // changes to config (auto-adds to config)
    }
}


class Metricz extends Metrics{

	public Metricz(String pluginName, String pluginVersion) throws IOException {
		super(pluginName, pluginVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFullServerVersion() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getPlayersOnline() {
		// TODO Auto-generated method stub
		return Minecraft.getMinecraft().getIntegratedServer().getCurrentPlayerCount();
	}

	@Override
	public File getConfigFile() {
		// TODO Auto-generated method stub
		return new File("metrics.cfg");
	}	
}