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

package com.EssencePVP.Listeners;

import com.EssencePVP.Player.Player;
import com.EssencePVP.character.Character;
import com.EssencePVP.managers.CharactersManager;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
//import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;

public class PlayerListener
{
	// Handle death event of a player
	@EventHandler
	@SideOnly(Side.SERVER)
	public void LivingDeathEvent(EntityLivingBase entity, DamageSource source){
		if(source.getEntity() != null){
			if(entity instanceof EntityClientPlayerMP && source.getEntity() instanceof EntityClientPlayerMP){
				// Give each player their exp
				Character p1 = (Character) Character.getPlayerMap().get((EntityClientPlayerMP)entity);
				Character p2 = (Character) Character.getPlayerMap().get((EntityClientPlayerMP)source.getEntity());
				
				// Player1 was killed by Player 2
				p1.getExp().expDeath(p2);
				p2.getExp().expKill(p1);
			}
		}
	}
	
	// new player!
	@EventHandler
	@SideOnly(Side.SERVER)
	public void EntityJoinWorldEvent(Entity entity, World world){
		// TODO: Add player to mysql or sqlite, if they dont exist. 
		// Otherwise, just add them to player map.
		if(entity instanceof EntityClientPlayerMP){

			Character c = new Character((EntityClientPlayerMP) entity); // auto-adds mapping
            CharactersManager.add(c);
		}
	}

    //TODO: When a player logouts, need to remove them from the characters manager.
}
