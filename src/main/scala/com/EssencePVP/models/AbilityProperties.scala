package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class AbilityProperty(id:Int, value:Int, Type:Int, description:String, name:String)

object AbilityProperties {
  class AbilityProperties(tag: Tag) extends Table[AbilityProperty](tag, "Ability_Property") {
    def id = column[Int]("ID")
    def value = column[Int]("VALUE")
    def Type = column[Int]("TYPE")
    def description = column[String]("DESCRIPTION")
    def name = column[String]("NAME")
    def * = (id, value, Type, description, name) <> (AbilityProperty.tupled, AbilityProperty.unapply)
  }
  val abilityProperty = TableQuery[AbilityProperties]

  def create(a:AbilityProperty) = {
    DB.withSession { implicit session =>
      abilityProperty += a
    }
  }
  def retrieve(id:Int): AbilityProperty = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === id).firstOption.get
    }
  }
  def update(a:AbilityProperty) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === a.id).update(a)
    }
  }
  def delete(id:Int) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === id).delete
    }
  }
  def delete(a:AbilityProperty) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === a.id).delete
    }
  }

}
