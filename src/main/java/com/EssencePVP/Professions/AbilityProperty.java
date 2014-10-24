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

public class AbilityProperty implements java.io.Serializable {
	private int iPropertyId;
	private String sPropertyName;
	private String sPropertyType;
	private float fPropertyValue;
	private AbilityProperty pNext;

	public AbilityProperty(int _iPropertyId, String _sPropertyName, String _sPropertyType, float _fPropertyValue){
		setPropertyId(_iPropertyId);
		setPropertyName(_sPropertyName);
		setPropertyType(_sPropertyType);
		setPropertyValue(_fPropertyValue);
		setNext(null);
	}

	public void setNext(AbilityProperty _pNext){
		this.pNext = _pNext;
	}

	public void setPropertyId(int _iPropertyId){
		iPropertyId = _iPropertyId;
	}

	public void setPropertyName(String _sPropertyName){
		sPropertyName = _sPropertyName;
	}

	public void setPropertyType(String _sPropertyType){
		sPropertyType = _sPropertyType;
	}

	public void setPropertyValue(float _fPropertyValue){
		fPropertyValue = _fPropertyValue;
	}

	public int getPropertyId(){
		return(this.iPropertyId);
	}

	public String getPropertyName(){
		return(this.sPropertyName);
	}

	public String getPropertyType(){
		return(this.sPropertyType);
	}

	public float getPropertyValue(){
		return(this.fPropertyValue);
	}

	public AbilityProperty getNext(){
		return(this.pNext);
	}

	public boolean isTail(){
		if(this.pNext == null)
			return true;
		else return false;
	}

}