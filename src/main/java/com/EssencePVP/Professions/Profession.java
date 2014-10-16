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

public class Profession{
	private int iProfessionId;
	private String sProfessionName;
	private String sProfesionDescription;
	private Abilities pAbilities;
	private Profession pNext;
	// possibly replace this by simply using 'Abilities' list
	// and avoid having an entire relational class
	// private ProfessionAbility pProfessionAbility;

	public Profession(){
		this(-1,null,null); // call other constructor
	}

	public Profession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription){
		setProfessionId(_iProfessionId);
		setProfessionName(_sProfessionName);
		setProfessionDescription(_sProfessionDescription);
		pAbilities = new Abilities();
		setNext(null);
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

	public boolean setNext(Profession _pNext){
		this.pNext = _pNext;
		return true;
	}

	// Description:
	// Automatically generates an iProfessionId prior to adding a new node. Will not work if this is not the tail. The
	// Proffession node will be automatically created using the passed Profession name and Profession Description
	// Returns:
	// false if the node we are trying to link to is not the tail node
	// true otherwise
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
		return(this.pNext);
	}

	public boolean isTail(){
		if(this.pNext == null)
			return true;
		else return false;
	}
}