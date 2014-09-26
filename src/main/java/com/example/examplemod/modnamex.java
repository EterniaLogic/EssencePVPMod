package com.example.examplemod;

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

import com.example.examplemod.blocks.*;
import com.example.examplemod.gui.ClientProxy;
import com.example.examplemod.gui.CreativeTabsEMod;

import net.minecraft.client.audio.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = modnamex.MODID, version = modnamex.VERSION)
public class modnamex
{
    public static final String MODID = "modnamex";
    public static final String VERSION = "0.1";
    
    public static Block lblk;
    public static Block tblk;
    public static Block hblk;
    
    public static int healblockid=0, trapblockid=0, levelblockid=0;
    
    public static CreativeTabs creativeTab = new CreativeTabsEMod("modnamex");
    
    // handles GUI synchronization
    @SidedProxy(clientSide = "com.example.examplemod.gui.ClientProxy", serverSide = "com.example.examplemod.gui.CommonProxy")
    public static ClientProxy proxy;

    // allows for methods to access this mod's raw members
    @Instance("modnamex")
    public static modnamex instance;
    
    
    public boolean useDBServer;
    public String mysqlServer;
    public int mysqlPort;
    public String mysqlUser;
    public String mysqlPassword;
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	instance = this;
    	//HelloWorld.doHello();
    	
    	// handles gui
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, this.proxy);
    	
    	Configuration config = new Configuration(new File("config/modnamex.cfg"),"0.1");
    	config.load();
    	
    	// changes to config (auto-adds to config)
    	useDBServer=config.getBoolean("UseDatabaseServer","ServerCFG",false,"Do you want to use a database server? If not, SQLite will be used.");
    	mysqlServer=config.getString("MysqlServer","ServerCFG","","Mysql Server location");
    	mysqlPort=config.getInt("MysqlPort","ServerCFG",3306,1,65535,"Mysql Port");
    	mysqlUser=config.getString("MysqlUser","ServerCFG","user","Mysql Username");
    	mysqlPassword=config.getString("MysqlPW","ServerCFG","pass","Mysql Password");
    	
    	healblockid = config.getInt("healblockid", "BlockIDs", 3380, 512, 40000, "Heal Block ID");
    	trapblockid = config.getInt("trapblockid", "BlockIDs", 3381, 512, 40000, "Trap Block ID");
    	levelblockid= config.getInt("levelblockid","BlockIDs", 3382, 512, 40000, "Level Block ID");
    	
    	
    	config.save();
    	
    	// Blocks
    	lblk = (new LevelBlock(levelblockid, 1)).setHardness(1.5F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName(modnamex.MODID + ":" + "textures/blocks/level_block");
    	tblk = (new TrapBlock(trapblockid, 1)).setHardness(1.5F).setResistance(25F).setStepSound(Block.soundTypeMetal).setBlockTextureName("modnamex:trap_block");
    	hblk = (new HealBlock(healblockid, 1)).setHardness(1.0F).setResistance(2F).setStepSound(Block.soundTypeWood).setBlockTextureName(modnamex.MODID + ":" + "textures/blocks/heal_block");
    	
    	
    	GameRegistry.registerBlock(hblk, "heal_block");
    	GameRegistry.registerBlock(tblk, "trap_block");
    	GameRegistry.registerBlock(lblk, "level_block");
    	
    	// Items
    
    	// Scala direct call
    	// Scala manages scripting for the mod
    	
    	// Java manages mod items, blocks, animations and performance
    }
}