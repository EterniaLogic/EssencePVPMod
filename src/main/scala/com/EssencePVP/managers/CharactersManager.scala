package com.EssencePVP.managers

import com.EssencePVP.character.Character

object CharactersManager {

  private var list:Vector[Character] = Vector()

  def add(c:Character) = list =  list :+ c

  def delete(c:Character) = list = list.filter(_ != c)

  def delete(playerName:String) = list = list.filter(_.getName != playerName)

  def getList : Vector[Character] = list

  def size : Int = list.size

  def length : Int = list.size //why not have both (size and length)? :)

}
