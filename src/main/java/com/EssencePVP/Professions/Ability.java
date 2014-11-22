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

public class Ability implements java.io.Serializable {
	private int iProperties;
	private int iAbilityId;
	private String sAbilityName;
	private String sAbilityDescription;
	private String sAbilityIcon;
	private Ability pNext;
	private AbilityProperty pProperties;

	public Ability(int _iAbilityId, String _sAbilityName, String _sAbilityDescription){
		this(_iAbilityId, _sAbilityName, _sAbilityDescription, "null");
	}

	public Ability(int _iAbilityId, String _iAbilityName, String _sAbilityDescription, String _sAbilityIcon){
		this.iProperties = 0;
		setAbilityId(_iAbilityId);
		setAbilityName(_iAbilityName);
		setAbilityDescription(_sAbilityDescription);
		setAbilityIcon(_sAbilityIcon);
		setNext(null);
	}

	public AbilityProperty addAbilityProperty(String _sPropertyName, String _sPropertyType, float _fPropertyValue){
		if(iProperties == 0)
			return(addAbilityProperty(1, _sPropertyName, _sPropertyType, _fPropertyValue));
		else
			return(addAbilityProperty((pProperties.getPropertyId()+1), _sPropertyName, _sPropertyType, _fPropertyValue));
	}

	public AbilityProperty addAbilityProperty(int _iPropertyId, String _sPropertyName, String _sPropertyType, float _fPropertyValue){
		if(iProperties == 0){
			if(_iPropertyId > 0)
				pProperties = new AbilityProperty(_iPropertyId, _sPropertyName, _sPropertyType, _fPropertyValue);
			else return null;
		}
		else{
			if(_iPropertyId > pProperties.getPropertyId()){
				AbilityProperty pTemporary = pProperties;
				pProperties = new AbilityProperty(_iPropertyId, _sPropertyName, _sPropertyType, _fPropertyValue);
				pProperties.setNext(pTemporary);
			} else return null;
		}
		++iProperties;
		return(pProperties);
	}

	public AbilityProperty getAbilityPropertyHead(){
		return(this.pProperties);
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

	private AbilityProperty getAbilityProperty(String _sPropertyName, AbilityProperty _pProperty){
		if(_pProperty == null)
			return null;
		else if(_pProperty.getPropertyName().equals(_sPropertyName))
			return(_pProperty);
		else
			return(getAbilityProperty(_sPropertyName, _pProperty.getNext()));
	}

	// delAbilityProperty

	public boolean setNext(Ability _pNext){
		this.pNext = _pNext;
		return true;
	}

	public boolean addNext(String _sAbilityName, String _sAbilityDescription){
		if(this.isTail()){
			this.pNext = new Ability((this.iAbilityId+1), _sAbilityName, sAbilityDescription);
			return true;
		}
		else return false;
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

	public void setAbilityIcon(String _sAbilityIcon){
		this.sAbilityIcon = _sAbilityIcon;
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

	public String getAbilityIcon(){
		return(this.sAbilityIcon);
	}

	public Ability getNext(){
		return(this.pNext);
	}

	public boolean isTail(){
		if(this.pNext == null)
			return true;
		else return false;
	}
}