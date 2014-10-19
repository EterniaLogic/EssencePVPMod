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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;

import com.EssencePVP.Player.Player;
import com.EssencePVP.Professions.Ability;
import com.EssencePVP.Professions.Profession;
import com.EssencePVP.Professions.Professions;
import com.EssencePVP.blocks.HealBlock;
import com.EssencePVP.blocks.LevelBlock;
import com.EssencePVP.blocks.TrapBlock;
import com.EssencePVP.gui.ClientProxy;
import com.EssencePVP.gui.CreativeTabsEMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = EssencePVP.MODID, version = EssencePVP.VERSION)
public class EssencePVP
{
    public static final String MODID = "essencepvp";
    public static final String VERSION = "0.1";
    
    private static Block hLevelBlock,hTrapBlock,hHealBlock;
    private static int healblockid=0, trapblockid=0, levelblockid=0;
    
    private static CreativeTabs creativeTab = new CreativeTabsEMod("EssencePVP");
    
    // handles GUI synchronization
    @SidedProxy(clientSide = "com.EssencePVP.gui.ClientProxy", serverSide = "com.EssencePVP.gui.CommonProxy")
    public static ClientProxy proxy;

    // allows for methods to access this mod's raw members
    @Instance("EssencePVP")
    private static EssencePVP instance;
    
    private Configuration config;
 
    // mySQL   
    private String bMySQLHostname,sMySQLUsername,sMySQLPassword;
    private int iMySQLPort;
    
	private double dExperienceRate;
	private boolean bGainExp, bUseMySQL;
    private Metricz metrics=null;
    private float fFundsGainRate,fScoreGainRate,meleeAttackF,rangeAttackF,rangeAttackS,techyAttackF,techyAttackS,magicAttackF,magicAttackS,passvFactor,KillRatio,DeathRatio;
	
	// Primary thread initializer
    @SidedProxy(clientSide = "com.EssencePVP.ClientThread", serverSide = "com.EssencePVP.ServerThread")
    public static Thread thread; // Primary thread figures stuff out
    
    private Logger logger; 			// Logs information and errors
	private Player clientplayer; 	// Client Player that is currently playing
    
    

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
    	instance = this;
    	logger = Logger.getLogger("EssencePVP");
    	logger.setLevel(Level.FINEST);
    	//HelloWorld.doHello();
    	
    	// handles gui
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, this.proxy);
    	if(Minecraft.getMinecraft().getIntegratedServer() != null){
	    	if(Minecraft.getMinecraft().getIntegratedServer().isServerRunning()){
	    		try {
					metrics = new Metricz("EssencePVP",VERSION);
				} catch (IOException e) {}
	    	}
    	}
    	
    	logger.log(Level.FINEST, "Net handler set up");
    	
    	// Generate or load the config
    	generateLoadConfig();
    	logger.log(Level.FINEST, "Config loaded");
    	
    	LoadBlocksItems();
        ProfessionsSetup();
        
        // Primary Thread for processing skills, player data, ect.
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
        	clientplayer = new Player(Minecraft.getMinecraft().thePlayer);
        }
        thread.start();
    }


	private void LoadBlocksItems() {
		// Load Blocks
    	hLevelBlock = (new LevelBlock(levelblockid, 1)).setHardness(1.5F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName(EssencePVP.MODID + ":" + "level_block");
    	hTrapBlock = (new TrapBlock(trapblockid, 1)).setHardness(1.5F).setResistance(25F).setStepSound(Block.soundTypeMetal).setBlockTextureName(EssencePVP.MODID + ":" + "trap_block");
    	hHealBlock = (new HealBlock(healblockid, 1)).setHardness(1.0F).setResistance(2F).setStepSound(Block.soundTypeWood).setBlockTextureName(EssencePVP.MODID + ":" + "heal_block");
    	
    	GameRegistry.registerBlock(hHealBlock, "heal_block");
    	GameRegistry.registerBlock(hTrapBlock, "trap_block");
    	GameRegistry.registerBlock(hLevelBlock, "level_block");
    	
    	// Items
	}


	private void ProfessionsSetup() {
		// <begin> EssencePvP::Professions::* examples
        Professions pTest = new Professions(); // Creating a Professions object

        // Adding Profession #1 (using method 1)
        Profession pTemporaryProfession = pTest.addProfession("Healer", "This is a healer class test"); // Adding a Profession to our Professions list (pTest)
        Ability pTemporaryAbility = pTemporaryProfession.getAbilities().addAbility("Foo","Bar"); // Adding an Ability to a Profession
        pTemporaryAbility.addAbilityProperty("test_property","cast_time",1.0f); // Adding a Property to an Ability
        System.out.println("#########################");
        System.out.println("Added: "+pTest.getLastAddedProfession().getProfessionName());

        // Adding Profession #2 (using method 2)
        pTest.addProfession("Guardian", "This is a tank class test"); // Adding a Profession to our Professions list (pTest)
        pTest.getLastAddedProfession().getAbilities().addAbility("Foo","Bar"); // Adding an Ability to a Profession
        pTest.getLastAddedProfession().getAbilities().getLastAddedAbility().addAbilityProperty("test_property","cast_time",1.0f); // Adding a Property to an Ability
        System.out.println("#########################");
        System.out.println("Added: "+pTest.getLastAddedProfession().getProfessionName());

        // EssencePvP::Professions::* </end>
        
        // load in ability categories if they exist. Else, make it.
    	if(!config.getCategory("Abilities").isEmpty()){
    		Set s = config.getCategory("Abilities").getChildren();
    	}else{
    		//TODO: Generate ability categories
    	}
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
    	
    	// Balance configurations
    	meleeAttackF=config.getFloat("MeleeAttackDamage","Balance",1.0f,0.00001f,65535.0f,"Multiple of melee attack damage");
    	meleeAttackF=config.getFloat("MeleeAttackSpeed","Balance",1.0f,0.00001f,65535.0f,"Multiple of melee attack speed");
    	rangeAttackF=config.getFloat("RangeAttackDamage","Balance",1.0f,0.00001f,65535.0f,"Multiple of ranged attack damage");
    	rangeAttackS=config.getFloat("RangeAttackSpeed","Balance",1.0f,0.00001f,65535.0f,"Multiple of ranged attack speed");
    	magicAttackF=config.getFloat("MagicAttackDamage","Balance",1.0f,0.00001f,65535.0f,"Multiple of magical attack damage");
    	magicAttackS=config.getFloat("MagicAttackSpeed","Balance",1.0f,0.00001f,65535.0f,"Multiple of magical attack speed");
    	techyAttackF=config.getFloat("TechnicalAttackDamage","Balance",1.0f,0.00001f,65535.0f,"Multiple of technical attack damage");
    	techyAttackS=config.getFloat("TechnicalAttackSpeed","Balance",1.0f,0.00001f,65535.0f,"Multiple of technical attack speed");
    	passvFactor =config.getFloat("PassiveFactor","Balance",1.0f,0.00001f,65535.0f,"How effective are passive skills?");
    	
    	KillRatio =config.getFloat("KillRatio","Balance",1.0f,0.00001f,65535.0f,"How much exp do you get upon killing? (Level difference taken into account)");
    	DeathRatio=config.getFloat("DeathRatio","Balance",1.0f,0.00001f,65535.0f,"How much exp do you lose upon death?");
    	
    	
    	config.save(); // changes to config (auto-adds to config)
    }
    
    
    
    
    
    
    
    
    
    
    /**
	 * @return the modid
	 */
	public static String getModid() {
		return MODID;
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return VERSION;
	}

	/**
	 * @return the hLevelBlock
	 */
	public static Block gethLevelBlock() {
		return hLevelBlock;
	}

	/**
	 * @return the hTrapBlock
	 */
	public static Block gethTrapBlock() {
		return hTrapBlock;
	}

	/**
	 * @return the hHealBlock
	 */
	public static Block gethHealBlock() {
		return hHealBlock;
	}

	/**
	 * @return the healblockid
	 */
	public static int getHealblockid() {
		return healblockid;
	}

	/**
	 * @return the trapblockid
	 */
	public static int getTrapblockid() {
		return trapblockid;
	}

	/**
	 * @return the levelblockid
	 */
	public static int getLevelblockid() {
		return levelblockid;
	}

	/**
	 * @return the creativeTab
	 */
	public static CreativeTabs getCreativeTab() {
		return creativeTab;
	}

	/**
	 * @return the proxy
	 */
	public static ClientProxy getProxy() {
		return proxy;
	}

	/**
	 * @return the instance
	 */
	public static EssencePVP getInstance() {
		return instance;
	}

	/**
	 * @return the config
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * @return the bUseMySQL
	 */
	public boolean isbUseMySQL() {
		return bUseMySQL;
	}

	/**
	 * @return the bMySQLHostname
	 */
	public String getbMySQLHostname() {
		return bMySQLHostname;
	}

	/**
	 * @return the iMySQLPort
	 */
	public int getiMySQLPort() {
		return iMySQLPort;
	}

	/**
	 * @return the sMySQLUsername
	 */
	public String getsMySQLUsername() {
		return sMySQLUsername;
	}

	/**
	 * @return the sMySQLPassword
	 */
	public String getsMySQLPassword() {
		return sMySQLPassword;
	}

	/**
	 * @return the dExperienceRate
	 */
	public double getdExperienceRate() {
		return dExperienceRate;
	}

	/**
	 * @return the fFundsGainRate
	 */
	public float getfFundsGainRate() {
		return fFundsGainRate;
	}

	/**
	 * @return the fScoreGainRate
	 */
	public float getfScoreGainRate() {
		return fScoreGainRate;
	}

	/**
	 * @return the bGainExp
	 */
	public boolean isbGainExp() {
		return bGainExp;
	}

	/**
	 * @return the metrics
	 */
	public Metricz getMetrics() {
		return metrics;
	}

	/**
	 * @return the meleeAttackF
	 */
	public float getMeleeAttackF() {
		return meleeAttackF;
	}

	/**
	 * @return the rangeAttackF
	 */
	public float getRangeAttackF() {
		return rangeAttackF;
	}

	/**
	 * @return the rangeAttackS
	 */
	public float getRangeAttackS() {
		return rangeAttackS;
	}

	/**
	 * @return the techyAttackF
	 */
	public float getTechyAttackF() {
		return techyAttackF;
	}

	/**
	 * @return the techyAttackS
	 */
	public float getTechyAttackS() {
		return techyAttackS;
	}

	/**
	 * @return the magicAttackF
	 */
	public float getMagicAttackF() {
		return magicAttackF;
	}

	/**
	 * @return the magicAttackS
	 */
	public float getMagicAttackS() {
		return magicAttackS;
	}

	/**
	 * @return the passvFactor
	 */
	public float getPassvFactor() {
		return passvFactor;
	}

	/**
	 * @return the killRatio
	 */
	public float getKillRatio() {
		return KillRatio;
	}

	/**
	 * @return the deathRatio
	 */
	public float getDeathRatio() {
		return DeathRatio;
	}

	/**
	 * @return the thread
	 */
	public static Thread getThread() {
		return thread;
	}
	
	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}
	
	/**
	 * @return the clientplayer
	 */
	public Player getClientplayer() {
		return clientplayer;
	}

}


class Metricz extends Metrics{

	public Metricz(String pluginName, String pluginVersion) throws IOException {
		super(pluginName, pluginVersion);
	}

	@Override
	public String getFullServerVersion() {
		return "";
	}

	@Override
	public int getPlayersOnline() {
		return Minecraft.getMinecraft().getIntegratedServer().getCurrentPlayerCount();
	}

	@Override
	public File getConfigFile() {
		return new File("metrics.cfg");
	}	
}