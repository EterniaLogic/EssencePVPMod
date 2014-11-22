package com.EssencePVP.managers

import com.EssencePVP._
import scala.util.Try

object AbilitiesManager {

  def load : Professions.Abilities = { //load from DB into the list
    def loadAbilityProperties(abilityList: Professions.Abilities): Professions.Abilities = { //load from the AbilityProperty table
      models.AbilityProperties.retrieveAll.foreach(element => {
        val ability = Try(abilityList.getAbility(element.ability)) //get by the ability id
        if(ability.isSuccess) { //if that exists, then add the property to that ability.
          ability.get.addAbilityProperty(
            element.id,
            element.name,
            element.Type.toString,
            element.value.toFloat
          )
        }
      })
      abilityList
    }

    val abilityList = new Professions.Abilities

    models.Abilities.retrieveAll().foreach(element => { //look at ea. element in the DB and add it to the list
      abilityList.addAbility(element.id, element.name, element.description) //add to the list
    })
    loadAbilityProperties(abilityList)
  }
  def add(property:Int, name:String, description:String, abilityList:Professions.Abilities) : Professions.Ability  = {
    val newAbility = abilityList.addAbility(name, description) //add to the list
    models.Abilities.create(models.Ability(newAbility.getAbilityId, "", name, description)) //add to the DB
    newAbility //return the newAbility
  }
  def setIcon(id:Int, icon:String) = {
    val ability = models.Abilities.retrieve(id)
    models.Abilities.update(models.Ability(
      ability.id,
      icon,
      ability.description,
      ability.name
    ))
  }
  def setIcon(name:String, icon:String) = {
    val ability = models.Abilities.retrieve(name)
    models.Abilities.update(models.Ability(
      ability.id,
      icon,
      ability.description,
      ability.name
    ))
  }
  def get(id:Int, abilityList:Professions.Abilities) : Professions.Ability = {
    abilityList.getAbility(id) //get the ability from the list by id
  }
  def get(name:String, abilityList:Professions.Abilities) : Professions.Ability = {
    abilityList.getAbility(name) //get the ability from the list by name
  }
  def del(id:Int, abilityList:Professions.Abilities) : Unit = {
    models.Abilities.delete(id) //delete from DB by id
    abilityList.delAbility(id) //delete from the list by id
  }
  def del(name:String, abilityList:Professions.Abilities) : Unit = {
    models.Abilities.delete(name) //delete from DB by name
    abilityList.delAbility(name) //delete from list by name
  }
}
