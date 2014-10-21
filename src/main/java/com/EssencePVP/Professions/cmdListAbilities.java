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

public class cmdListAbilities extends CommandBase{
	private Professions pProfessions;
	
	public cmdListAbilities(Professions _pProfessions){
		this.pProfessions = _pProfessions;
	}

	@Override
	public String getCommandName(){
		return "listabilities";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender){
		return("Displays a list of all a given profession's abilities. Usage: listabilities <profession id>");
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] aString){
		if(aString.length != 1)
			iCommandSender.addChatMessage(new ChatComponentText("invalid # of arguments"));
		else
			printAbility(iCommandSender, aString[0]);
	}

	private void printAbility(ICommandSender iCommandSender, String _sProfessionId){
		int iProfessionId = Integer.parseInt(_sProfessionId);
		Profession pProfession = pProfessions.getProfession(iProfessionId);

		Ability pAbility = pProfession.getAbilities().getAbilitiesHead();
		iCommandSender.addChatMessage(new ChatComponentText("Listing abilities for "+pProfession.getProfessionName()));

		printAbility(iCommandSender, pAbility);
	}

	private void printAbility(ICommandSender iCommandSender, Ability pAbility){
		if(pAbility == null)
			return;
		else{
			String sAbilityId = Integer.toString(pAbility.getAbilityId());
			String sAbilityName = pAbility.getAbilityName();
			String sAbilityDescription = pAbility.getAbilityDescription();

			iCommandSender.addChatMessage(new ChatComponentText(sAbilityId+" - "+sAbilityName+" ("+sAbilityDescription+")"));
			printAbility(iCommandSender, pAbility.getNext());
		}
	}
}