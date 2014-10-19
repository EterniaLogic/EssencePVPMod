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

// AK

package com.EssencePVP.Professions;

public class ProfessionAbility implements java.io.Serializable
{
	int iProfessionAbilityId;
	int iTreePosition;
	// <?> modifiers;
	ProfessionAbility pNext;
	Profession pProfession;
	Ability pAbility;

	public ProfessionAbility(){
		this(-1, null, null);
	}

	public ProfessionAbility(int _iProfessionAbilityId, Profession _pProfession, Ability _pAbility){
		setProfession(_pProfession);
		setAbility(_pAbility);

	}

	public void setProfessionAbilityId(int _iProfessionAbilityId){
		iProfessionAbilityId = _iProfessionAbilityId;
	}

	public void setProfession(Profession _pProfession){
		pProfession = _pProfession;
	}

	public void setAbility(Ability _pAbility){
		pAbility = _pAbility;
	}

	public void setNext(ProfessionAbility _pNext){
		this.pNext = _pNext;
	}

	public boolean addNext(Profession _pProfession, Ability _pAbility){
		if(this.isTail()){
			this.pNext = new ProfessionAbility(this.iProfessionAbilityId+1, _pProfession, _pAbility);
			return true;
		}
		else return false;
	}

	public int getProfessionAbilityId(){
		return(this.iProfessionAbilityId);
	}

	public ProfessionAbility getNext(){
		return(this.pNext);
	}

	public Profession getProfession(){
		return(this.pProfession);
	}

	public Ability getAbility(){
		return(this.pAbility);
	}

	public boolean isTail(){
		if(this.pNext == null)
			return true;
		else return false;
	}
}