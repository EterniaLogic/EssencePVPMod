package com.EssencePVP.character

import com.EssencePVP.Player.Player
import net.minecraft.client.entity.EntityClientPlayerMP
import com.EssencePVP.models.{Character => Char, Profession, Characters}

class Character(clientPlayer:EntityClientPlayerMP) extends Player(clientPlayer) with Serializable {

  private final val defaultID = -1
  private final val defaultName = ""
  private final val defaultPlayerUID = ""
  private final val defaultClassAbilities = -1
  private final val defaultFactionID = -1

  private var characterObj:Char = null
  private var profession:Profession = null

  retrieveChar

  def getName : String = characterObj.playerName

  def getPlayerUID : String = characterObj.playerUID

  def getClassAbilities : Int = characterObj.classAbilities

  def getFactionID : Int = characterObj.factionID

  def getID : Int = {
    if(characterObj.id == -1) { //0a. Did we already get the id?
      if (Characters.exists(characterObj.playerName)) //1a. Check to see if player exists in the DB already.
        Characters.retrieve(characterObj.playerName).id //2a. Player Exists - Get ID from DB.
      else  //2b. Player Does Not Exists - Create Player, Get ID
        addPlayer
    } else //0b. Ah, we already got the ID from the DB.
      characterObj.id
  }

  def setFactionID(factionID:Int) = {
    characterObj = Char(characterObj.id, characterObj.playerUID, characterObj.classAbilities, characterObj.playerName, factionID)
    updatePlayer
  }

  def setClassAbilities(classAbilities:Int) = {
    characterObj = Char(characterObj.id, characterObj.playerUID, classAbilities, characterObj.playerName, characterObj.factionID)
    updatePlayer
  }

  private def setName(name:String) = {
    characterObj = Char(characterObj.id, characterObj.playerUID, characterObj.classAbilities, name, characterObj.factionID)
    updatePlayer
  }

  private def setPlayerUID(playerUID:String) = {
    characterObj = Char(characterObj.id, playerUID, characterObj.classAbilities, characterObj.playerName, characterObj.factionID)
    updatePlayer
  }

  private def setID(id:Int) = {
    characterObj = Char(id, characterObj.playerUID, characterObj.classAbilities, characterObj.playerName, characterObj.factionID)
    updatePlayer
  }

  private def addPlayer : Int = { //adds the player to the DB
    val id = Characters.maxID() + 1
    Characters.create(characterObj)
    id
  }

  private def updatePlayer = Characters.update(characterObj) //update the DB

  private def retrieveChar = {
    if(playerExists)
      characterObj = Characters.retrieve(characterObj.id)
    else {
      characterObj = Char(getID, defaultPlayerUID, defaultClassAbilities, defaultName, defaultFactionID)
      addPlayer
    }
  }

  private def playerExists : Boolean = Characters.exists(characterObj.playerName)

}
