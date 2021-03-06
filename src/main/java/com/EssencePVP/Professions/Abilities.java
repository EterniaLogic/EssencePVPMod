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

public class Abilities implements java.io.Serializable {
	private int iNumAbilities;
	private Ability pHead;
	private Ability pLast;

	//private hAbility hList;

	public Abilities(){
		this.iNumAbilities = 0;
		this.pHead = null;
		this.pLast = null;
		//this.hList = new hAbility();
	}

	// Description:
	// Creates an Ability object and adds it to the head of an Abilities list given the Ability's Name and Description.
	// Special note should be made as the pHead will always have the highest iAbilityId where as the tail of the list
	// will have the smallest iAbilityId
	public Ability addAbility(String _sAbilityName, String _sAbilityDescription){
		if(iNumAbilities == 0)
			return(addAbility(1, _sAbilityName, _sAbilityDescription));
		else
			return(addAbility((pHead.getAbilityId()+1), _sAbilityName, _sAbilityDescription));
	}

	public Ability addAbility(int _iAbilityId, String _sAbilityName, String _sAbilityDescription){
		if(iNumAbilities == 0)
			if(_iAbilityId > 0)
				pHead = new Ability(_iAbilityId, _sAbilityName, _sAbilityDescription);
			else return null;
		else{
			if(_iAbilityId > pHead.getAbilityId()){
				Ability pTemporary = pHead;
				pHead = new Ability(_iAbilityId, _sAbilityName, _sAbilityDescription);
				pHead.setNext(pTemporary);
			} else return null;
		}
		//hList.regElement(pHead);
		++iNumAbilities;
		return(pHead);
	}

	// Description:
	// Gets the ability by name, retreives its ID and then passes it to the delAbility(int _iAbilityId) function
	public void delAbility(String _sAbilityName){
		Ability pAbility = getAbility(_sAbilityName);
		if(pAbility != null)
			delAbility(pAbility.getAbilityId());
	}

	// Description:
	// Passes the given _iAbilityId along with the head of the Abilities list to the delAbility(int _iAbility, 
	// Ability _pAbility) function
	public void delAbility(int _iAbilityId){
		delAbility(_iAbilityId, this.pHead);
	}

	// Description:
	// Recursivley scans an Ability list and removes an ability out of the Abilities list given an Ability's iAbilityID
	private void delAbility(int _iAbilityId, Ability _pAbility){
		if(_pAbility == null)
			return;
		else{
			if(this.pHead.getAbilityId() == _iAbilityId){
				//hList.unregElement(_pAbility);
				this.pHead = this.pHead.getNext();
				this.iNumAbilities--;
				return;
			}
			else{
				if(_pAbility.getNext() != null){
					if(_pAbility.getNext().getAbilityId() == _iAbilityId){
						//hList.unregElement(_pAbility);
						_pAbility.setNext(_pAbility.getNext().getNext());
						this.iNumAbilities--;
					}
				}
				else delAbility(_iAbilityId, _pAbility.getNext());
			}
		}
		return;
	}

	public Ability getAbilitiesHead(){
		return(this.pHead);
	}

	public Ability getLastAddedAbility(){
		return(getAbilitiesHead());
	}

	public Ability getAbility(int _iAbilityId){
		if(this.pLast != null){
			if(this.pLast.getAbilityId() == _iAbilityId)
				return(this.pLast);
		}
		this.pLast = getAbility(_iAbilityId, pHead);
		return(this.pLast);
	}

	public Ability getAbility(String _sAbilityName){
		if(this.pLast != null){
			if(this.pLast.getAbilityName().equals(_sAbilityName))
				return(this.pLast);
		}
		this.pLast = getAbility(_sAbilityName, pHead);
		return(this.pLast);
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
	 		return(getAbility(_sAbilityName, _pAbility.getNext()));
	}

	public int getAbilitiesCount(){
		return(this.iNumAbilities);
	}

	// Returns:
	// false if the Abilities list is empty
	// true otherwise
	public boolean isAbilitiesEmpty(){
		if(getAbilitiesCount() > 0)
			return false;
		else return true;
	}

	private class hAbility{
		private int iHashSize; // The current used slots
		private int iHashSpace; // The hash's maximum capacity
		private Ability[] pAbilities;

		public hAbility(){
			this(1);
		}

		public hAbility(int _iHashSpace){
			iHashSize = 0;
			iHashSpace = _iHashSpace;
			pAbilities = new Ability[_iHashSpace];
		}

		public void regElement(Ability _pAbility){
			if(iHashSize < iHashSpace){
				pAbilities[iHashSize] = _pAbility;
				iHashSize++;
			}
			else{
				pAbilities = rebuildHash(); // Expand our array
				regElement(_pAbility);
			}
		}

		private Ability[] rebuildHash(){
			iHashSpace = iHashSpace*2;
			Ability[] pTemporary = new Ability[iHashSpace];
			return(duplicateData(0, pTemporary)); // Duplicate the data and return the new array
		}

		private Ability[] duplicateData(int _iIndex, Ability[] pDestination){
			if(_iIndex >= iHashSize)
				return(pDestination);
			else{
				pDestination[_iIndex] = pAbilities[_iIndex];
				return(duplicateData(++_iIndex, pDestination));
			}
		}

		public void unregElement(Ability _pAbility){
			if(iHashSize <= 0)
				return;
			else
				unregElement(0, _pAbility);
		}

		private void unregElement(int _iIndex, Ability _pAbility){
			if(_iIndex > iHashSize)
				return;
			else{
				if(pAbilities[_iIndex] == _pAbility){
					pAbilities[_iIndex] = null;
					iHashSize--;

					shiftLeft(_iIndex);
				}
				else
					unregElement(++_iIndex, _pAbility);
			}
		}

		private void shiftLeft(int _iIndex){
			if(_iIndex >= iHashSize)
				return;
			else{
				if(pAbilities[_iIndex] == null){
					pAbilities[_iIndex] = pAbilities[_iIndex+1];
					pAbilities[_iIndex+1] = null;
					shiftLeft(++_iIndex);
				}
				return;
			}
		}
	}
}