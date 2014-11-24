package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Character(id:Int, classAbilities:Int, profession:Int, playerName:String, factionID:Int)

object Characters {
  class Characters(tag: Tag) extends Table[Character](tag, "Characters") {
    def id = column[Int]("ID")
    def classAbilities = column[Int]("CLASSABILITIES")
    def profession = column[Int]("PROFESSION")
    def playerName = column[String]("PLAYERNAME")
    def factionID = column[Int]("FACTIONID")
    def * = (id, classAbilities, profession, playerName, factionID) <> (Character.tupled, Character.unapply)
  }
  val characters = TableQuery[Characters]

  def create(a:Character)  = {
    DB.withSession { implicit session =>
      characters += a
    }
  }
  def retrieve(id:Int): Character = {
    DB.withSession { implicit session =>
      characters.filter(_.id === id).firstOption.get
    }
  }
  def retrieve(playerName:String) : Character = {
    DB.withSession { implicit session =>
      characters.filter(_.playerName === playerName).firstOption.get
    }
  }
  def maxID() : Int = {
    DB.withSession { implicit session =>
      characters.map(_.id).max.run getOrElse 0
    }
  }
  def update(a:Character) = {
    DB.withSession { implicit session =>
      characters.filter(_.id === a.id).update(a)
    }
  }

  def delete(id:Int) = {
    DB.withSession { implicit session =>
      characters.filter(_.id === id).delete
    }
  }
  def delete(a:Character) = {
    DB.withSession { implicit session =>
      characters.filter(_.id === a.id).delete
    }
  }
  
}
