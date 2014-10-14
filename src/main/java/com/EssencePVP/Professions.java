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

package com.EssencePVP;

public class Professions{
	private int iNumProfessions; // This number must be equal to the sProffesionId value of the tail node
	private Profession pHead;

	public Professions(){
		iNumProfessions = 0;
		pHead = null;
	}

	// This will continously add items to the head in order to perform this task in O(1) time as opposed
	// to adding elements to the tail which would take O(n) time. At this time I do not believe that the
	// order to which items are added is relevant
	// - AK
	public void addProfession(String _sProfessionName, String _sProfessionDescription){
		if(iNumProfessions == 0){
			pHead = new Profession(++iNumProfessions, _sProfessionName, _sProfessionDescription);
		} else {
			Profession pTemporary = pHead;
			pHead = new Profession(++iNumProfessions, _sProfessionName, _sProfessionDescription);
			pHead.setNext(pTemporary);
		}
		return;
	}

	// This should be used as little as possible. It will call a recursive function to locate the correct
	// Profession object; identify its ID and pass it to the appropriate delProfession function to actually
	// delete it. It is best to already know a Profession's ID. This can be improved by creating a function
	// which has the object in question passed to it and then performing the delete
	// - AK
	public void delProfession(String _sProfessionName){
		delProfession(_sProfessionName, this.pHead);
	}

	public void delProfession(String _sProfessionName, Profession _pProfession){
		if(_pProfession == null)
			return;
		else if(_pProfession.getProfessionName().equals(_sProfessionName)){
			delProfession(_pProfession.getProfessionId());
			return;
		}
		else{
			delProfession(_sProfessionName, _pProfession.getNext());
		}
	}

	public void delProfession(int _iProfessionId){
		delProfession(_iProfessionId, this.pHead);
	}

	// Recursively traverse through the list and locate the node that must be deleted. Once the node is located
	// the parent node will link to whatever that node is linking to. I am assuming that JAVA's garbage collection
	// will delete the node that is no longer refrenced as I have not found any form of an equivelant to C's delete()
	// - AK
	private void delProfession(int _iProfessionId, Profession _pProfession){ // Untested
		if(_pProfession == null)
			return;
		else{
			if(_pProfession == this.pHead){
				this.pHead = this.pHead.getNext();
				this.iNumProfessions--;
				return;
			}
			else{
				if(_pProfession.getNext() != null){
					if(_pProfession.getNext().getProfessionId() == _iProfessionId){
						_pProfession.setNext(_pProfession.getNext().getNext());
						this.iNumProfessions--;
					}
				}
				else delProfession(_iProfessionId, _pProfession.getNext());
			}
		}
			return;
	}

	// delProfession should utilize these functions to search for nodes as opposed to having its own
	// search algorithim
	public Profession getProfession(int _iProfessionId){
		return(getProfession(_iProfessionId, pHead));
	}

	public Profession getProfession(int _iProfessionId, Profession _pProfession){
		if(_pProfession == null)
			return null;
		else if(_pProfession.getProfessionId() == _iProfessionId)
			return(_pProfession);
		else
			return(getProfession(_iProfessionId, _pProfession.getNext()));
	}

	public Profession getProfession(String _sProfessionName){
		return(getProfession(_sProfessionName, pHead));
	}

	public Profession getProfession(String _sProfessionName, Profession _pProfession){
		if(_pProfession == null)
			return null;
		else if(_pProfession.getProfessionName().equals(_sProfessionName))
			return(_pProfession);
		else
	 		return(getProfession(_sProfessionName, _pProfession));
	}

	public int getProfessionCount(){
		return(this.iNumProfessions);
	}

	public boolean isProfessionsEmpty(){
		if(getProfessionCount() > 0)
			return false;
		else return true;
	}
}