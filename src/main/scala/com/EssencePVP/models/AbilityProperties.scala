package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class AbilityProperty(id:Int, value:Int, Type:Int, description:String, name:String, ability:Int)

object AbilityProperties {
  class AbilityProperties(tag: Tag) extends Table[AbilityProperty](tag, "Ability_Property") {
    def id = column[Int]("ID")
    def value = column[Int]("VALUE")
    def Type = column[Int]("TYPE")
    def description = column[String]("DESCRIPTION")
    def name = column[String]("NAME")
    def ability = column[Int]("ABILITY")
    def * = (id, value, Type, description, name, ability) <> (AbilityProperty.tupled, AbilityProperty.unapply)
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
  def retrieveAll : List[AbilityProperty] = {
    DB.withSession { implicit session =>
      val q = for {
        p <- abilityProperty.sortBy(_.id.asc) //order by id in asc order
      } yield p
      q.list
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
