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

public class Profession implements java.io.Serializable
{
	private int iProfessionId;
	private String sProfessionName;
	private String sProfesionDescription;
	private String sProfessionIcon;
	private Abilities pAbilities;
	private Profession pNext;

	public Profession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription){
		this(_iProfessionId, _sProfessionName, _sProfessionDescription, null);
	}

	public Profession(int _iProfessionId, String _sProfessionName, String _sProfessionDescription, String _sProfessionIcon){
		setProfessionId(_iProfessionId);
		setProfessionName(_sProfessionName);
		setProfessionDescription(_sProfessionDescription);
		setProfessionIcon(_sProfessionIcon);
		pAbilities = new Abilities();
		setNext(null);		
	}

	// TODO: Add functions to add items into this Profession's Abilities list

	public void setProfessionId(int _iProfessionId){
		this.iProfessionId = _iProfessionId;
	}

	public void setProfessionName(String _sProfessionName){
		this.sProfessionName = _sProfessionName;
	}

	public void setProfessionDescription(String _sProfessionDescription){
		this.sProfesionDescription = _sProfessionDescription;
	}

	public void setProfessionIcon(String _sProfessionIcon){
		this.sProfessionIcon = _sProfessionIcon;
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

	public Abilities getAbilities(){
		return(this.pAbilities);
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

	public String getProfessionIcon(){
		return(this.sProfessionIcon);
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