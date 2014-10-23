package com.EssencePVP.managers

import com.EssencePVP._

object ProfessionsManager {

  def load : Professions.Professions = {
    val professionList = new Professions.Professions
    models.Professions.retrieveAll().foreach(element => { //iterate through element in the DB and add it to the list
      professionList.addProfession(element.id, element.name, element.description) //add to the list
    })
    professionList //return the list
  }
  def add(name:String, description:String, professionList:Professions.Professions) : Professions.Profession  = {
    val newProfession = professionList.addProfession(name, description)
    models.Professions.create(models.Profession(newProfession.getProfessionId, name, description))
    newProfession
  }
  def del(id:Int, professionList:Professions.Professions) : Unit = {
    models.Professions.delete(id)
    professionList.delProfession(id)
  }
  def del(name:String, professionList:Professions.Professions) : Unit = {
    models.Professions.delete(name)
    professionList.delProfession(name)
  }
}
