package com.EssencePVP.managers

import com.EssencePVP.Professions.Abilities
import com.EssencePVP.Professions.Ability
import com.EssencePVP.models.AbilityProperties
import com.EssencePVP.models.{ Abilities => AbilitiesDB }
import com.EssencePVP.models.{ Ability => AbilityModel }

import scala.util.Try

object AbilitiesManager {

  def load : Abilities = { //load from DB into the list

    def loadAbilityProperties(abilityList: Abilities): Abilities = { //load from the AbilityProperty table
      AbilityProperties.retrieveAll.foreach(element => {
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

    val abilityList = new Abilities

    AbilitiesDB.retrieveAll().foreach(element => { //look at ea. element in the DB and add it to the list
      abilityList.addAbility(element.id, element.name, element.description) //add to the list
    })

    loadAbilityProperties(abilityList)
  }

  def add(property:Int, name:String, profession:Int, description:String, icon:String, abilityList:Abilities) : Ability = {
    val tryToFind = Try(AbilitiesDB.retrieve(name))
    if(tryToFind.isSuccess)
      new Ability(tryToFind.get.id, name, description)
    else {
      val newAbility = abilityList.addAbility(name, description)
      AbilitiesDB.create(AbilityModel(newAbility.getAbilityId, icon, profession, description, name)) //add to the DB
      newAbility
    }
  }

  def add(property:Int, name:String, profession:Int, description:String, abilityList:Abilities) : Ability =
    add(property, name, profession, description, "", abilityList)

  def setIcon(id:Int, icon:String) = {
    val ability = AbilitiesDB.retrieve(id)
    AbilitiesDB.update(
      AbilityModel(
        ability.id,
        icon,
        ability.profession,
        ability.description,
        ability.name
      )
    )
  }

  def setIcon(name:String, icon:String) = {
    val ability = AbilitiesDB.retrieve(name)
    AbilitiesDB.update(
      AbilityModel(
        ability.id,
        icon,
        ability.profession,
        ability.description,
        ability.name
      )
    )
  }

  def get(id:Int, abilityList:Abilities) : Ability = {
    abilityList.getAbility(id) //get the ability from the list by id
  }

  def get(name:String, abilityList:Abilities) : Ability = {
    abilityList.getAbility(name) //get the ability from the list by name
  }

  def del(id:Int, abilityList:Abilities) : Unit = {
    AbilitiesDB.delete(id) //delete from DB by id
    abilityList.delAbility(id) //delete from the list by id
  }

  def del(name:String, abilityList:Abilities) : Unit = {
    AbilitiesDB.delete(name) //delete from DB by name
    abilityList.delAbility(name) //delete from list by name
  }

}
