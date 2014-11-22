package com.EssencePVP.character

import com.EssencePVP.Player.Player
import net.minecraft.client.entity.EntityClientPlayerMP
import com.EssencePVP.models.Characters
import com.EssencePVP.models.{Character => Char}

class Character(clientPlayer:EntityClientPlayerMP) extends Player(clientPlayer) with Serializable {
  private var name = ""
  private var playerUID = ""
  private var id = -1
  private var classAbilities = -1
  private var factionID = -1


  setName("Place Holder Name 1")
  setPlayerUID("Place Holder UID")
  setID


  def getName : String = this.name

  def getPlayerUID : String = this.playerUID

  def getClassAbilities : Int = this.classAbilities

  def getFactionID : Int = this.factionID

  def getID : Int = {
    if(this.id == -1) { //0a. Did we already get the id?
      if (Characters.exists(name)) //1a. Check to see if player exists in the DB already.
        Characters.retrieve(name).id //2a. Player Exists - Get ID from DB.
      else  //2b. Player Does Not Exists - Create Player, Get ID
        addPlayer
    } else //0b. Ah, we already got the ID from the DB.
      this.id
  }

  def setFactionID(factionID:Int) = {
    this.factionID = factionID
    updatePlayer
  }

  def setClassAbilities(classAbilities:Int) = {
    this.classAbilities = classAbilities
    updatePlayer
  }

  private def setName(name:String) = this.name = name

  private def setPlayerUID(playerUID:String) = this.playerUID = playerUID

  private def setID = this.id = getID

  private def addPlayer : Int = { //adds the player to the DB
    val id = Characters.maxID() + 1
    Characters.create(Char(id, playerUID, classAbilities, name, factionID))
    id
  }

  private def updatePlayer = Characters.update(Char(id, playerUID, classAbilities, name, factionID)) //update the DB

}
