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

package com.EssencePVP.Professions;

import net.minecraft.util.ChatComponentText;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class cmdAddProfession extends CommandBase{
	@Override
	public String getCommandName(){
		return "addprofession";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender){
		return("Adds a profession to the professions list. Usage: addProfession <Name> <Description>");
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] astring){
		//iCommandSender.canCommandSenderUseCommand
		iCommandSender.addChatMessage(new ChatComponentText("no"));
	}
}