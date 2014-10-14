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

public class Ability{
	private int iAbilityId;
	private String sAbilityName;
	private String sAbilityDescription;
	private AbilityProperty pProperties;

	public Ability(){
		this(-1,null,null);
	}

	public Ability(int _iAbilityId, String _iAbilityName, String _iAbilityDescription){
		setAbilityId(_iAbilityId);
		setAbilityName(_iAbilityName);
		setAbilityDescription(_iAbilityDescription);
	}

	public void setAbilityId(int _iAbilityId){
		iAbilityId = _iAbilityId;
	}

	public void setAbilityName(String _iAbilityName){
		sAbilityName = _iAbilityName;
	}

	public void setAbilityDescription(String _iAbilityDescription){
		sAbilityDescription = _iAbilityDescription;
	}

	public int getAbilityId(){
		return(this.iAbilityId);
	}

	public String getAbilityName(){
		return(this.sAbilityName);
	}

	public String getAbilityDescription(){
		return(this.sAbilityDescription);
	}

}