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

import java.util.LinkedList;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ClientThread extends Thread {
	//private List<Player> isFlying = Collections.synchronizedList(new ArrayList<Player>());
	//private ConcurrentHashMap<Player, Integer> playerTimes = new ConcurrentHashMap<Player, Integer>();
	private LinkedList<Runnable> tickList = new LinkedList<Runnable>();
	private static ClientThread instance;
	public ClientThread(){
		instance = this;
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public void run(){
		while (true){
			try {
				Thread.sleep(1000); // 1 Second
			} catch (InterruptedException e) {break;}
			
			// process skills, ect.
			EssencePVP.getInstance().getLogger().log(Level.FINEST, "Test Client Thread");
			
			if(Minecraft.getMinecraft().isSingleplayer()){
				// Game in single player!
			}else{
				// Game in multiplayer!
				// Get data from the server
			}
			
			// tick these commands
			for(Runnable r : tickList){
				r.run();
			}
		}
	}
	
	/**
	 * @return the tickList
	 */
	public LinkedList getTickList() {
		return tickList;
	}

	/**
	 * @return the instance
	 */
	public static ClientThread getInstance() {
		return instance;
	}
}
