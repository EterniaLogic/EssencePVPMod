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
/*
	public boolean delProfession(String _sProfessionName){
		// Find the ID of the Profession which has this ProfessionName
		// Then call the delProfession(int)
	}

	public boolean delProfession(int _iProfessionId){
		delProfession(_iProfessionId, this.pHead);
	}
*/
	// private Profession delProfession(int _iProfessionId, Profession _pProfession){
	// 	if(_pProfession == null)
	// 		return null;
	// 	else 
	// 		if(_pProfession.getProfessionId() == _iProfessionId)
	// 			return _pProfession.getNext();
	// 	else
	// 		delProfession(_iProfessionId, _pProfession);
	// }

	public int getProfessionCount(){
		return(this.iNumProfessions);
	}

	public boolean isProfessionsEmpty(){
		if(getProfessionCount() > 0)
			return false;
		else return true;
	}
}