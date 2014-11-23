package com.EssencePVP.managers

import com.EssencePVP.models.{ Professions => ProfessionDB }
import com.EssencePVP.models.{ Profession => ProfessionModel }
import com.EssencePVP.Professions.Professions
import com.EssencePVP.Professions.Profession

import scala.util.Try

object ProfessionsManager {

  def load : Professions = { //load from DB into the list
    val professionList = new Professions
    ProfessionDB.retrieveAll().foreach(element => { //look at ea. element in the DB and add it to the list
      professionList.addProfession(element.id, element.name, element.description) //add to the list
    })
    professionList //return the list
  }
  def add(name:String, description:String, professionList:Professions) : Profession  = {
    val newProfession = professionList.addProfession(name, description) //add the new profession to the list

    Try(ProfessionDB.retrieve(name)) getOrElse
      ProfessionDB.create(ProfessionModel(newProfession.getProfessionId, name, description, "")) //add to the DB

    newProfession //return the newProfession
  }
  def setIcon(id:Int, icon:String) = {
    val profession = ProfessionDB.retrieve(id)
    ProfessionDB.update(
      ProfessionModel(
        profession.id,
        profession.description,
        profession.name,
        icon
      )
    )
  }
  def setIcon(name:String, icon:String) = {
    val profession = ProfessionDB.retrieve(name)
    ProfessionDB.update(
      ProfessionModel(
        profession.id,
        profession.description,
        profession.name,
        icon
      )
    )
  }
  def get(id:Int, professionList:Professions) : Profession  = {
    professionList.getProfession(id) //get from the list by id
  }
  def get(name:String, professionList:Professions) : Profession = {
    professionList.getProfession(name) //get from the list by name
  }
  def del(id:Int, professionList:Professions) : Unit = {
    ProfessionDB.delete(id) //delete from the DB by id
    professionList.delProfession(id) //delete from the list by id
  }
  def del(name:String, professionList:Professions) : Unit = {
    ProfessionDB.delete(name) //delete from the DB by name
    professionList.delProfession(name) //delete from the list by name
  }
}
