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

private class Professions{
	private int iNumProfessions; // This number must be equal to the sProffesionId value of the tail node
	private Profession pHead;

	public Professions(){
		iNumProfessions = 0;
		pHead = null;
	}

	public void addProfession(String _sProfessionName, String _sProfessionDescription){
		if(iNumProfessions == 0){ // if the linked list is empty
			if(pHead == null)
				pHead = new Profession(1, _sProfessionName, _sProfessionDescription);
		} else{
			Profession pTemporary = pHead;
			while(!pHead.isTail()){
				pTemporary = pTemporary.getNext();
			}
			if(pTemporary.isTail()){
				pTemporary.addNext(_sProfessionName, _sProfessionDescription);
			}

		}
		iNumProfessions++;
	}

	// Needs re-write. Possibly should be made recursive
	public boolean delProfession(int _iProfessionId){
		Profession pTemporary = pHead;
		// Replace pHead with the next item on the list
		// if pHead.pNext == null then it will automatically
		// adapt
		if(pTemporary.getProfessionId() == _iProfessionId){
			pHead = pTemporary.getNext();
			iNumProfessions--;
			return true;
		}
		else{
			while(!(pTemporary.pNext().getProfessionId() == _iProfessionId)){

			}
		}
	}

	public int getProfessionCount(){
		return(this.iNumProfessions);
	}
}