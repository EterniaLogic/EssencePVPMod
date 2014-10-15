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

public class Ability{
	private int iAbilityId;
	private String sAbilityName;
	private String sAbilityDescription;
	private AbilityProperty pProperties;
	private int iProperties;

	public Ability(){
		this(-1,null,null);
	}

	public Ability(int _iAbilityId, String _iAbilityName, String _iAbilityDescription){
		iProperties = 0;
		setAbilityId(_iAbilityId);
		setAbilityName(_iAbilityName);
		setAbilityDescription(_iAbilityDescription);
	}

	public void addAbilityProperty(String _sPropertyName, String _sPropertyType, float _fPropertyValue){
		if(iProperties == 0){
			pProperties = new AbilityProperty(++iProperties, _sPropertyName, _sPropertyType, _fPropertyValue);
		} else {
			AbilityProperty pTemporary = pProperties;
			pProperties = new AbilityProperty(++iProperties, _sPropertyName, _sPropertyType, _fPropertyValue);
			pProperties.setNext(pTemporary);
		}
		return;
	}

	public AbilityProperty getAbilityProperty(int _iPropertyId){
		return(getAbilityProperty(_iPropertyId, pProperties));
	}

	public AbilityProperty getAbilityProperty(String _sPropertyName){
		return(getAbilityProperty(_sPropertyName, pProperties));
	}

	private AbilityProperty getAbilityProperty(int _iPropertyId, AbilityProperty _pProperty){
		if(_pProperty == null)
			return null;
		else if(_pProperty.getPropertyId() == _iPropertyId)
			return(_pProperty);
		else
			return(getAbilityProperty(_iPropertyId, _pProperty.getNext()));
	}

	public AbilityProperty getAbilityProperty(String _sPropertyName, AbilityProperty _pProperty){
		if(_pProperty == null)
			return null;
		else if(_pProperty.getPropertyName().equals(_sPropertyName))
			return(_pProperty);
		else
			return(getAbilityProperty(_sPropertyName, _pProperty.getNext()));
	}

	// delAbilityProperty

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