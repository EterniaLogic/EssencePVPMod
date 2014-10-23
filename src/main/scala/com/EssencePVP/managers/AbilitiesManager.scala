package com.EssencePVP.managers

import com.EssencePVP._

object AbilitiesManager {

  def load : Professions.Abilities = {
    val abilityList = new Professions.Abilities
    models.Abilities.retrieveAll().foreach(element => { //iterate through element in the DB and add it to the list
      abilityList.addAbility(element.id, element.name, element.description) //add to the list
    })
    abilityList //return the list
  }
  def add(property:Int, name:String, description:String, abilityList:Professions.Abilities) : Professions.Ability  = {
    val newAbility = abilityList.addAbility(name, description)
    models.Abilities.create(models.Ability(newAbility.getAbilityId, property, name, description))
    newAbility
  }
  def get(id:Int, abilityList:Professions.Abilities) : Professions.Ability = {
    abilityList.getAbility(id)
  }
  def get(name:String, abilityList:Professions.Abilities) : Professions.Ability = {
    abilityList.getAbility(name)
  }
  def del(id:Int, abilityList:Professions.Abilities) : Unit = {
    models.Abilities.delete(id)
    abilityList.delAbility(id)
  }
  def del(name:String, abilityList:Professions.Abilities) : Unit = {
    models.Abilities.delete(name)
    abilityList.delAbility(name)
  }
}
