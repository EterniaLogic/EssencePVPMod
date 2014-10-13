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

private class Profession{
	private int iProfessionId;
	private String sProfessionName;
	private String sProfesionDescription;
	private Profession pNext;
	// private ProfessionAbility pProfessionAbility;

	// public Profession(){
	// 	this(-1,null,null); // call other constructor
	// }

	public Profession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription){
		setProfessionId(_iProfessionId);
		setProfessionName(_sProfessionName);
		setProfessionDescription(_sProfessionDescription);
	}

	public void setProfessionId(int _iProfessionId){
		this.iProfessionId = _iProfessionId;
	}

	public void setProfessionName(String _sProfessionName){
		this.sProfessionName = _sProfessionName;
	}

	public void setProfessionDescription(String _sProfessionDescription){
		this.sProfesionDescription = _sProfessionDescription;
	}

	// Automatically generates a iProfessionId prior to adding a new node. Will not work if this is not the tail
	public boolean addNext(String _sProfessionName, String _sProfessionDescription){
		if(this.isTail()){
			this.pNext = new Profession((this.iProfessionId+1), _sProfessionName, sProfesionDescription);
			return true;
		}
		else return false;
	}

	public int getProfessionId(){
		return(this.iProfessionId);
	}

	public String getProfessionName(){
		return(this.sProfessionName);
	}

	public String getProfessionDescription(){
		return(this.sProfesionDescription);
	}

	public Profession getNext(){
		return(pNext);
	}

	public boolean isTail(){
		if(pNext == null){
			return true;
		} else return false;
	}
}