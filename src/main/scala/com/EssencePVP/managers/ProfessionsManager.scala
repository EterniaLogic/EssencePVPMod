package com.EssencePVP.managers

import com.EssencePVP._

object ProfessionsManager {

  def load : Professions.Professions = { //load from DB into the list
    val professionList = new Professions.Professions
    models.Professions.retrieveAll().foreach(element => { //look at ea. element in the DB and add it to the list
      professionList.addProfession(element.id, element.name, element.description) //add to the list
    })
    professionList //return the list
  }
  def add(name:String, description:String, professionList:Professions.Professions) : Professions.Profession  = {
    val newProfession = professionList.addProfession(name, description) //add the new profession to the list
    models.Professions.create(models.Profession(newProfession.getProfessionId, name, description)) //add to the DB
    newProfession //return the newProfession
  }
  def get(id:Int, professionList:Professions.Professions) : Professions.Profession = {
    professionList.getProfession(id) //get from the list by id
  }
  def get(name:String, professionList:Professions.Professions) : Professions.Profession = {
    professionList.getProfession(name) //get from the list by name
  }
  def del(id:Int, professionList:Professions.Professions) : Unit = {
    models.Professions.delete(id) //delete from the DB by id
    professionList.delProfession(id) //delete from the list by id
  }
  def del(name:String, professionList:Professions.Professions) : Unit = {
    models.Professions.delete(name) //delete from the DB by name
    professionList.delProfession(name) //delete from the list by name
  }
}
