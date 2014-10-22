package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class ProfessionAbility(id:Int, modifier:Int, treePosition:Int, abilityID:Int, classID:Int)

object ProfessionAbilities {
  class ProfessionAbilities(tag: Tag) extends Table[ProfessionAbility](tag, "Profession_Ability") {
    def id = column[Int]("ID")
    def modifier = column[Int]("MODIFIER")
    def treePosition = column[Int]("TREEPOSITION")
    def abilityID = column[Int]("ABILITYID")
    def classID = column[Int]("CLASSID")
    def * = (id, modifier, treePosition, abilityID, classID) <> (ProfessionAbility.tupled, ProfessionAbility.unapply)
  }
  val abilityProperty = TableQuery[ProfessionAbilities]

  def create(a:ProfessionAbility) = {
    DB.withSession { implicit session =>
      abilityProperty += a
    }
  }
  def retrieve(id:Int): ProfessionAbility = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === id).firstOption.get
    }
  }
  def update(a:ProfessionAbility) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === a.id).update(a)
    }
  }
  def delete(id:Int) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === id).delete
    }
  }
  def delete(a:ProfessionAbility) = {
    DB.withSession { implicit session =>
      abilityProperty.filter(_.id === a.id).delete
    }
  }

}
