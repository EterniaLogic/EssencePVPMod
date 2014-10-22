package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Faction(leader:String, id:Int, grade:Int, regionsID:Int)

object Factions {
  class Factions(tag: Tag) extends Table[Faction](tag, "Factions") {
    def leader = column[String]("LEADER")
    def id = column[Int]("ID")
    def grade = column[Int]("GRADE")
    def regionsID = column[Int]("REGIONSID")
    def * = (leader, id, grade, regionsID) <> (Faction.tupled, Faction.unapply)
  }
  val factions = TableQuery[Factions]

  def create(a:Faction) = {
    DB.withSession { implicit session =>
      factions  += a
    }
  }
  def retrieve(id:Int): Faction = {
    DB.withSession { implicit session =>
      factions.filter(_.id === id).firstOption.get
    }
  }
  def update(a:Faction) = {
    DB.withSession { implicit session =>
      factions.filter(_.id === a.id).update(a)
    }
  }
  def delete(id:Int) = {
    DB.withSession { implicit session =>
      factions.filter(_.id === id).delete
    }
  }
  def delete(a:Faction) = {
    DB.withSession { implicit session =>
      factions.filter(_.id === a.id).delete
    }
  }

}
