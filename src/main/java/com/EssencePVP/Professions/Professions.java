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

public class Professions implements java.io.Serializable {
	private int iNumProfessions; // This is the current number of professions in the list
	private Profession pHead;
	private Profession pLast;

	private hProfession hList;

	public Professions(){
		this.iNumProfessions = 0;
		this.pHead = null;
		this.pLast = null;
		this.hList = new hProfession(20);
	}

	public Profession addProfession(String _sProfessionName, String _sProfessionDescription){
		if(iNumProfessions == 0)
			return(addProfession(1, _sProfessionName, _sProfessionDescription, null));
		else
			return(addProfession((pHead.getProfessionId()+1), _sProfessionName, _sProfessionDescription, null));
	}

	public Profession addProfession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription){
		return(addProfession(_iProfessionId, _sProfessionName, _sProfessionDescription, null));
	}

	public Profession addProfession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription, String _sProfessionIcon){
		if(iNumProfessions == 0)
			if(_iProfessionId > 0)
				pHead = new Profession(_iProfessionId, _sProfessionName, _sProfessionDescription, _sProfessionIcon);
			else return null;
		else{
			if(_iProfessionId > pHead.getProfessionId()){
				Profession pTemporary = pHead;
				pHead = new Profession(_iProfessionId, _sProfessionName, _sProfessionDescription, _sProfessionIcon);
				pHead.setNext(pTemporary);
			} else return null;
		}
		hList.regElement(pHead);
		++iNumProfessions;
		return(pHead);
	}

	public void delProfession(String _sProfessionName){
		Profession pProfession = getProfession(_sProfessionName);
		if(pProfession != null)
			delProfession(pProfession.getProfessionId());
	}

	public void delProfession(int _iProfessionId){
		delProfession(_iProfessionId, this.pHead);
	}

	private void delProfession(int _iProfessionId, Profession _pProfession){
		if(_pProfession == null)
			return;
		else{
			if(this.pHead.getProfessionId() == _iProfessionId){
				hList.unregElement(_pProfession);
				this.pHead = this.pHead.getNext();
				this.iNumProfessions--;
			}
			else{
				if(_pProfession.getNext() != null){
					if(_pProfession.getNext().getProfessionId() == _iProfessionId){
						hList.unregElement(_pProfession.getNext());
						_pProfession.setNext(_pProfession.getNext().getNext());
						this.iNumProfessions--;
					}
				}
				else delProfession(_iProfessionId, _pProfession.getNext());
			}
		}
		return;
	}

	public Profession getProfessionsHead(){
		return(this.pHead);
	}

	public Profession getLastAddedProfession(){
		return(getProfessionsHead());
	}

	public Profession getProfession(int _iProfessionId){
		// If another getProfession() request is sent for the Profession with Id of the last request
		// we will not recursivley try to locate it as we already know where it is. A specific address
		// table (which is an array with an object's refrence in the slot given by the Id) could be
		// implemented to store the locations of all professions. At this time I do not see this as a
		// requirement
		if(this.pLast != null){
			if(pLast.getProfessionId() == _iProfessionId)
				return(this.pLast);
		}
		this.pLast = getProfession(_iProfessionId, pHead);
		return(this.pLast);
	}

	public Profession getProfession(String _sProfessionName){
		if(this.pLast != null){
			if(this.pLast.getProfessionName().equals(_sProfessionName))
				return(this.pLast);
		}
		this.pLast = getProfession(_sProfessionName, pHead);
		return(this.pLast);
	}

	private Profession getProfession(int _iProfessionId, Profession _pProfession){
		if(_pProfession == null)
			return null;
		else if(_pProfession.getProfessionId() == _iProfessionId)
			return(_pProfession);
		else
			return(getProfession(_iProfessionId, _pProfession.getNext()));
	}

	private Profession getProfession(String _sProfessionName, Profession _pProfession){
		if(_pProfession == null)
			return null;
		else if(_pProfession.getProfessionName().equals(_sProfessionName))
			return(_pProfession);
		else
	 		return(getProfession(_sProfessionName, _pProfession.getNext()));
	}

	public int getProfessionCount(){
		return(this.iNumProfessions);
	}

	public boolean isProfessionsEmpty(){
		if(getProfessionCount() > 0)
			return false;
		else return true;
	}

	public void listAll(){
		hList.listAll();
	}

	private class hProfession{
		private int iHashSize; // The current used slots
		private int iHashSpace; // The hash's maximum capacity
		private Profession[] pProfessions;

		public hProfession(){
			this(10);
		}

		public hProfession(int _iHashSpace){
			iHashSize = 0;
			iHashSpace = _iHashSpace;
			pProfessions = new Profession[_iHashSpace];
		}

		public void listAll(){
			for(int i=0; i<iHashSize; i++)
				System.out.println(pProfessions[i].getProfessionName());
			System.out.println(iHashSize);
		}

		public void regElement(Profession _pProfession){
			if(iHashSize < iHashSpace){
				pProfessions[iHashSize] = _pProfession;
				iHashSize++;
			}
			else{
				// build new array
			}
		}

		public void unregElement(Profession _pProfession){
			if(iHashSize <= 0)
				return;
			else{
				unregElement(0, _pProfession);
			}
		}

		private void unregElement(int _iIndex, Profession _pProfession){
			if(_iIndex > iHashSize)
				return;
			else{
				if(pProfessions[_iIndex] == _pProfession){
					pProfessions[_iIndex] = null;
					iHashSize--;

					shiftLeft(_iIndex);
				}
				else
					unregElement(++_iIndex, _pProfession);
			}
		}

		private void shiftLeft(int _iIndex){
			if(_iIndex >= iHashSize)
				return;
			else{
				if(pProfessions[_iIndex] == null){
					pProfessions[_iIndex] = pProfessions[_iIndex+1];
					pProfessions[_iIndex+1] = null;
					shiftLeft(++_iIndex);
				}
				return;
			}
		}
	}
}