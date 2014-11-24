package com.EssencePVP.character

import com.EssencePVP.Player.Player
import net.minecraft.client.entity.EntityClientPlayerMP
import com.EssencePVP.models.{Character => Char, Profession, Characters}
import scala.util.Try

//TODO: char table needs a foreign column to ref. to profession

class Character(clientPlayer:EntityClientPlayerMP) extends Player(clientPlayer) with Serializable {

  private final val defaultID = -1
  private final val defaultName = "Fake Name" //needs to be pulled from MC - crashes
  private final val defaultProfession = -1
  private final val defaultClassAbilities = -1
  private final val defaultFactionID = -1

  private var characterObj = Char(defaultID, defaultClassAbilities, defaultProfession, defaultName, defaultFactionID)
  private var profession:Profession = null

  retrieveChar //constructor

  def getName : String = characterObj.playerName

  def getClassAbilities : Int = characterObj.classAbilities

  def getFactionID : Int = characterObj.factionID

  def getID : Int = characterObj.id

  def setFactionID(factionID:Int) = {
    characterObj = Char(characterObj.id, characterObj.classAbilities, characterObj.profession, characterObj.playerName, factionID)
    updatePlayer
  }

  def setClassAbilities(classAbilities:Int) = {
    characterObj = Char(characterObj.id, classAbilities, characterObj.profession, characterObj.playerName, characterObj.factionID)
    updatePlayer
  }

  private def addPlayer : Int = { //adds the player to the DB

    Try(Characters.retrieve(characterObj.playerName).id) getOrElse {
      Characters.create(
        Char(
          Characters.maxID() + 1,
          defaultClassAbilities,
          defaultProfession,
          defaultName,
          defaultFactionID
        )
      )
    }

  }

  private def updatePlayer = Characters.update(characterObj) //update the DB

  private def retrieveChar = Try(characterObj = Characters.retrieve(characterObj.id)) getOrElse addPlayer

}
