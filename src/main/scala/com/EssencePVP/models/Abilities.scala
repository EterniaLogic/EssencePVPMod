package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Ability(id:Int, icon:String, property:Int, description:String, name:String)

object Abilities {
  class Abilities(tag: Tag) extends Table[Ability](tag, "Ability") {
    def id = column[Int]("ID")
    def icon = column[String]("ICON")
    def property = column[Int]("PROPERTY")
    def description = column[String]("DESCRIPTION")
    def name = column[String]("NAME")
    def * = (id, icon, property, description, name) <> (Ability.tupled, Ability.unapply)
  }
  val abilities = TableQuery[Abilities]

  def create(a:Ability) = {
    DB.withSession { implicit session =>
      abilities += a
    }
  }
  def retrieve(id:Int): Ability = {
    DB.withSession { implicit session =>
      abilities.filter(_.id === id).firstOption.get
    }
  }
  def retrieveAll() : List[Ability] = {
    DB.withSession { implicit session =>
      val q = for {
        p <- abilities.sortBy(_.id.asc) //order by id in asc order
      } yield p
      q.list
    }
  }
  def update(a:Ability) = {
    DB.withSession { implicit session =>
      abilities.filter(_.id === a.id).update(a)
    }
  }
  def delete(id:Int) = {
    DB.withSession { implicit session =>
      abilities.filter(_.id === id).delete
    }
  }
  def delete(name:String) = {
    DB.withSession { implicit session =>
      abilities.filter(_.name === name).delete
    }
  }
  def delete(a:Ability) = {
    DB.withSession { implicit session =>
      abilities.filter(_.id === a.id).delete
    }
  }
}
