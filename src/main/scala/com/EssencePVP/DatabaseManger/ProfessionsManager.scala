package com.EssencePVP.DatabaseManger

import com.EssencePVP._

object ProfessionsManager {

  def load : Professions.Professions = {
    val professionList = new Professions.Professions
    models.Professions.retrieveAll().foreach(element => { //iterate through element in the DB and add it to the list
      professionList.addProfession(element.name, element.description) //add to the list
    })
    professionList //return the list
  }

}
