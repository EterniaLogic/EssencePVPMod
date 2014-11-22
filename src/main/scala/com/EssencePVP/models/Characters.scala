package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Character(id:Int, playerUID:String, classAbilities:Int, playerName:String, factionID:Int)

object Characters {
  class Characters(tag: Tag) extends Table[Character](tag, "Characters") {
    def id = column[Int]("ID")
    def playerUID = column[String]("PLAYERUID")
    def classAbilities = column[Int]("CLASSABILITIES")
    def playerName = column[String]("PLAYERNAME")
    def factionID = column[Int]("FACTIONID")
    def * = (id, playerUID, classAbilities, playerName, factionID) <> (Character.tupled, Character.unapply)  
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
  def exists(playerName:String) : Boolean = {
    DB.withSession { implicit session =>
      characters.filter(_.playerName === playerName).exists.run
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
