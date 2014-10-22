package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Ability(id:Int, property:Int, description:String, name:String)

object Abilities {
  class Abilities(tag: Tag) extends Table[Ability](tag, "Ability") {
    def id = column[Int]("ID")
    def property = column[Int]("PROPERTY")
    def description = column[String]("DESCRIPTION")
    def name = column[String]("NAME")
    def * = (id, property, description, name) <> (Ability.tupled, Ability.unapply)
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
  def delete(a:Ability) = {
    DB.withSession { implicit session =>
      abilities.filter(_.id === a.id).delete
    }
  }
}
