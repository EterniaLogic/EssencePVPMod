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

import com.EssencePVP.EssencePVP;

// Uses event listener

// Server-side
public class Experience
{
  Experience(float exp, Player p){}
  
  private float totalexp=0.0f;
  private Player player;
  
  // Player kills another
  public void expKill(Player killed){
	  // TODO: Set EXP gain from kill
	  // IDEA: If a player kills a player of higher level, they get more.
	  player.exp.totalexp += (killed.exp.getLevel()/player.exp.getLevel())
			  						*EssencePVP.getInstance().getKillRatio();
  }
  
  // Player dies
  public void expDeath(Player killer){
	  player.exp.totalexp -= (player.exp.getLevel()/killer.exp.getLevel())
				*EssencePVP.getInstance().getDeathRatio();
  }
  
  // Returns the level based off of 2^n
  public double getLevel(){
	  return Math.log(totalexp+4)/Math.log(2.0); // Base 2 log (Change of basis)
  }

	public float getTotalexp() {
		return totalexp;
	}
	
	public void setTotalexp(float totalexp) {
		this.totalexp = totalexp;
	}
}
