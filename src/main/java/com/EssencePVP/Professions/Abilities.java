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

public class Abilities{
	private int iNumAbilities;
	private Ability pHead;

	public Abilities(){
		this.iNumAbilities = 0;
		this.pHead = null;
	}

	public void addAbility(String _sAbilityName, String _sAbilityDescription){
		if(iNumAbilities == 0)
			pHead = new Ability(++iNumAbilities, _sAbilityName, _sAbilityDescription);
		else{
			Ability pTemporary = pHead;
			pHead = new Ability(++iNumAbilities, _sAbilityName, _sAbilityDescription);
			pHead.setNext(pTemporary);
		}
		return;
	}

	public void delAbility(String _sAbilityName){
		delAbility(getAbility(_sAbilityName).getAbilityId());
	}

	public void delAbility(int _iAbilityId){
		delAbility(_iAbilityId, this.pHead);
	}

	private void delAbility(int _iAbilityId, Ability _pAbility){
		if(_pAbility == null)
			return;
		else{
			if(_pAbility == this.pHead){
				this.pHead = this.pHead.getNext();
				this.iNumAbilities--;
				return;
			}
			else{
				if(_pAbility.getNext() != null){
					if(_pAbility.getNext().getAbilityId() == _iAbilityId){
						_pAbility.setNext(_pAbility.getNext().getNext());
						this.iNumAbilities--;
					}
				}
				else delAbility(_iAbilityId, _pAbility.getNext());
			}
		}
			return;
	}

	public Ability getAbility(int _iAbilityId){
		return(getAbility(_iAbilityId, pHead));
	}

	public Ability getAbility(String _sAbilityName){
		return(getAbility(_sAbilityName, pHead));
	}

	private Ability getAbility(int _iAbilityId, Ability _pAbility){
		if(_pAbility == null)
			return null;
		else if(_pAbility.getAbilityId() == _iAbilityId)
			return(_pAbility);
		else
			return(getAbility(_iAbilityId, _pAbility.getNext()));
	}

	private Ability getAbility(String _sAbilityName, Ability _pAbility){
		if(_pAbility == null)
			return null;
		else if(_pAbility.getAbilityName().equals(_sAbilityName))
			return(_pAbility);
		else
	 		return(getAbility(_sAbilityName, _pAbility));
	}

	public int getAbilitiesCount(){
		return(this.iNumAbilities);
	}

	public boolean isAbilitiesEmpty(){
		if(getAbilitiesCount() > 0)
			return false;
		else return true;
	}
}