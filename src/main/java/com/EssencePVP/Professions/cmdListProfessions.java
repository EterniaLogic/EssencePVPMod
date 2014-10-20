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

public class cmdListProfessions extends CommandBase{
	private Professions pProfessions;
	
	public cmdListProfessions(Professions _pProfessions){
		pProfessions = _pProfessions;
	}

	@Override
	public String getCommandName(){
		return "listprofessions";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender){
		return("Displays a list of all professions available");
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] aString){
		printProfession(iCommandSender, pProfessions.getProfessionsHead());
	}

	private void printProfession(ICommandSender iCommandSender, Profession pProfession){
		if(pProfession == null)
			return;
		else{
			String sProfessionId = Integer.toString(pProfession.getProfessionId());
			String sProfessionName = pProfession.getProfessionName();
			String sProfessionDescription = pProfession.getProfessionDescription();
			iCommandSender.addChatMessage(new ChatComponentText(sProfessionId+" - "+sProfessionName+" ("+sProfessionDescription+")"));
			printProfession(iCommandSender, pProfession.getNext());
		}
	}
}