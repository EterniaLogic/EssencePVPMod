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

public class cmdListProperties extends CommandBase{
	private Professions pProfessions;
	
	public cmdListProperties(Professions _pProfessions){
		this.pProfessions = _pProfessions;
	}

	@Override
	public String getCommandName(){
		return "listproperties";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender){
		return("Displays a list of all a given ability's properties. Usage: listaproperties <profession id> <ability id>");
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] aString){
		if(aString.length != 2)
			iCommandSender.addChatMessage(new ChatComponentText("invalid # of arguments"));
		else
			printProperty(iCommandSender, aString[0], aString[1]);
	}

	private void printProperty(ICommandSender iCommandSender, String _sProfessionId, String _sAbilityId){
		int iProfessionId = 0;
		int iAbilityId = 0;
		try{ // Make sure the string the user has passed actually contains a number
			iProfessionId = Integer.parseInt(_sProfessionId);
			iAbilityId = Integer.parseInt(_sAbilityId);
		} catch(NumberFormatException eError){
				iCommandSender.addChatMessage(new ChatComponentText("You must enter a numeric value"));
				return;
		}
		Profession pProfession = pProfessions.getProfession(iProfessionId);
		if(pProfession == null){ // Make sure if we have located the profession in question
			iCommandSender.addChatMessage(new ChatComponentText("Profession not found. Check your profession id. A list of professions and their ids can be found with /listprofessions."));
			return;
		} else{
			Ability pAbility = pProfession.getAbilities().getAbility(iAbilityId);
			if(pAbility == null){
				iCommandSender.addChatMessage(new ChatComponentText("Ability not found"));
				return;
			} else{
				AbilityProperty pProperty = pAbility.getAbilityPropertyHead();
				iCommandSender.addChatMessage(new ChatComponentText("Listing properties for "+pProfession.getProfessionName()+"."+
					pAbility.getAbilityName()));
				printProperty(iCommandSender, pProperty);
			}
		}
	}

	private void printProperty(ICommandSender iCommandSender, AbilityProperty pProperty){
		if(pProperty == null)
			return;
		else{
			String sPropertyId = Integer.toString(pProperty.getPropertyId());
			String sPropertyName = pProperty.getPropertyName();
			String sPropertyType = pProperty.getPropertyType();
			String sPropertyValue = Float.toString(pProperty.getPropertyValue());

			iCommandSender.addChatMessage(new ChatComponentText(sPropertyId+" - "+sPropertyName+":"+sPropertyType+" "+sPropertyValue));
			printProperty(iCommandSender, pProperty.getNext());
		}
	}
}