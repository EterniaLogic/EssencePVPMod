package com.EssencePVP.models

import DataSource._
import scala.slick.driver.MySQLDriver.simple._

case class Profession(id:Int, description:String, name:String, icon:String)

object Professions {
  class Professions(tag: Tag) extends Table[Profession](tag, "Profession") {
    def id = column[Int]("ID")
    def description = column[String]("DESCRIPTION")
    def name = column[String]("NAME")
    def icon = column[String]("ICON")
    def * = (id, description, name, icon) <> (Profession.tupled, Profession.unapply)
  }
  val professions = TableQuery[Professions]

  def create(a:Profession) = {
    DB.withSession { implicit session =>
      professions += a
    }
  }
  def retrieve(id:Int): Profession = {
    DB.withSession { implicit session =>
      professions.filter(_.id === id).firstOption.get
    }
  }
  def retrieveAll() : List[Profession] = { //pulls all data from the Professions table
    DB.withSession { implicit session =>
      val q = for {
        p <- professions.sortBy(_.id.asc) //order by id in asc order
      } yield p
      q.list
    }
  }
  def update(a:Profession) = {
    DB.withSession { implicit session =>
      professions.filter(_.id === a.id).update(a)
    }
  }
  def delete(id:Int) = {
    DB.withSession { implicit session =>
      professions.filter(_.id === id).delete
    }
  }
  def delete(name:String) = {
    DB.withSession { implicit session =>
      professions.filter(_.name === name).delete
    }
  }
  def delete(a:Profession) = {
    DB.withSession { implicit session =>
      professions.filter(_.id === a.id).delete
    }
  }
}
