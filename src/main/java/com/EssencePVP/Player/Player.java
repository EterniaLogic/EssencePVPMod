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

package com.EssencePVP.Player;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;


public class Player implements java.io.Serializable
{
	private static HashMap playerMap = new HashMap();
	Experience exp = new Experience(0, this); // Experience manager
	EntityClientPlayerMP mcplayer;
	
	public Player(EntityClientPlayerMP player){
		// load player based on name
		// TODO:  Load player from Mysql or SQLite
		this.mcplayer = player;
		playerMap.put(player, this);
	}
	
	public void playerLogout(){
		playerMap.remove(mcplayer);
	}
	
	/**
	 * @return the playerMap
	 */
	public static HashMap getPlayerMap() {
		return playerMap;
	}

	/**
	 * @return the mcplayer
	 */
	public EntityClientPlayerMP getMcplayer() {
		return mcplayer;
	}

	/**
	 * @return the exp
	 */
	public Experience getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(Experience exp) {
		this.exp = exp;
	}
}
