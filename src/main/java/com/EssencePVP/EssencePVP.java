package com.EssencePVP;

import java.io.File;

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

import net.minecraft.client.audio.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = EssencePVP.MODID, version = EssencePVP.VERSION)
public class EssencePVP
{
    public static final String MODID = "essencepvp";
    public static final String VERSION = "0.1";
    
    public static Block lblk; // hLevelBlock
    public static Block tblk; // hTrapBlock
    public static Block hblk; // hHealBlock

    public static int healblockid=0, trapblockid=0, levelblockid=0;
    
    public static CreativeTabs creativeTab = new CreativeTabsEMod("EssencePVP");
    
    // handles GUI synchronization
    @SidedProxy(clientSide = "com.EssencePVP.gui.ClientProxy", serverSide = "com.EssencePVP.gui.CommonProxy")
    public static ClientProxy proxy;

    // allows for methods to access this mod's raw members
    @Instance("EssencePVP")
    public static EssencePVP instance;
    
    public Configuration config;
    
    public boolean useDBServer;
    public String mysqlServer;
    public int mysqlPort;
    public String mysqlUser;
    public String mysqlPassword;
	public double expRate;
	public float monRate;
	public float scoRate;
	public boolean accumExp;
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	instance = this;
    	//HelloWorld.doHello();
    	
    	// handles gui
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, this.proxy);
    	
    	
    	// Generate or load the config
    	generateLoadConfig();
    	
    	// Load Blocks
    	lblk = (new LevelBlock(levelblockid, 1)).setHardness(1.5F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName(EssencePVP.MODID + ":" + "level_block");
    	tblk = (new TrapBlock(trapblockid, 1)).setHardness(1.5F).setResistance(25F).setStepSound(Block.soundTypeMetal).setBlockTextureName(EssencePVP.MODID + ":" + "trap_block");
    	hblk = (new HealBlock(healblockid, 1)).setHardness(1.0F).setResistance(2F).setStepSound(Block.soundTypeWood).setBlockTextureName(EssencePVP.MODID + ":" + "heal_block");
    	
    	GameRegistry.registerBlock(hblk, "heal_block");
    	GameRegistry.registerBlock(tblk, "trap_block");
    	GameRegistry.registerBlock(lblk, "level_block");
    	
    	// Items
    
    	// Scala direct call
    	// Scala manages scripting for the mod
    	
    	// Java manages mod items, blocks, animations and performance
    }
    
    public void generateLoadConfig(){
    	config = new Configuration(new File("config/EssencePVP.cfg"),"0.1");
    	config.load();

    	// Mod configurations
    	expRate = config.getFloat("ExpRate","GameCFG",1.0f,0.1f,10000.0f,"Set the exp rate for the skill tree");
    	monRate = config.getFloat("MoneyRate","GameCFG",1.0f,0.1f,10000.0f,"Sets the speed in which players get money from pvp");
    	scoRate = config.getFloat("ScoreRate","GameCFG",1.0f,0.1f,10000.0f,"Sets the speed in which players get score from pvp");
    	accumExp= config.getBoolean("AccumulateExp","ServerCFG",true,"Will players gain exp from hitting other players? If not, they get exp from killing others");
    	
    	// Mysql Server stuffs
    	useDBServer=config.getBoolean("UseDatabaseServer","ServerCFG",false,"Do you want to use a database server? If not, SQLite will be used.");
    	mysqlServer=config.getString("MysqlServer","ServerCFG","","Mysql Server location");
    	mysqlPort=config.getInt("MysqlPort","ServerCFG",3306,1,65535,"Mysql Port");
    	mysqlUser=config.getString("MysqlUser","ServerCFG","user","Mysql Username");
    	mysqlPassword=config.getString("MysqlPW","ServerCFG","pass","Mysql Password");
    	
    	// Block configurations
    	healblockid = config.getInt("healblockid", "BlockIDs", 3380, 512, 40000, "Heal Block ID");
    	trapblockid = config.getInt("trapblockid", "BlockIDs", 3381, 512, 40000, "Trap Block ID");
    	levelblockid= config.getInt("levelblockid","BlockIDs", 3382, 512, 40000, "Level Block ID");
    	
    	config.save(); // changes to config (auto-adds to config)
    }
}