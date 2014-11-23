package com.EssencePVP.managers

import com.EssencePVP.character.Character


object CharactersManager {
  private var list:Vector[Character] = Vector()

  def add(c:Character) = list = list :+ c

  def delete(c:Character) = list = list.filter(_ != c)

  def getList : Vector[Character] = list

}
