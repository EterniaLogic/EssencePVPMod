package com.EssencePVP.character

import com.EssencePVP.Player.Player
import net.minecraft.client.entity.EntityClientPlayerMP
import com.EssencePVP.models.Characters
import com.EssencePVP.models.{Character => Char}

class Character(clientPlayer:EntityClientPlayerMP) extends Player(clientPlayer) with Serializable {
  private var name:String = ""
  private var playerUID:String = ""
  private var id:Int = -1

  def Character(client:EntityClientPlayerMP) = {
    setName(client.getDisplayName)
    setPlayerUID(client.getUniqueID.toString)
    setID
  }

  def Character(name:String, playerUID:String) = {
    setName(name)
    setPlayerUID(playerUID)
    setID
  }

  def Character(name:String) = {
    setName(name)
    setPlayerUID(clientPlayer.getUniqueID.toString)
    setID
  }

  def Character() = {
    setName(clientPlayer.getDisplayName)
    setPlayerUID(clientPlayer.getUniqueID.toString)
    setID
  }

  def getName : String = this.name

  def getPlayerUID : String = this.playerUID

  def getID : Int = {

    if(this.id == -1) { //0a. Did we already get the id?
      if (Characters.exists(name)) //1. Check to see if player exists in the DB already.
        Characters.retrieve(name).id //2a. Player Exists - Get ID from DB.
      else {
        //2b. Player Does Not Exists - Create Player, Get ID
        Characters.create(Char(-1, playerUID.toString, -1, name, -1)) //This returns the AutoInc ID.
      }
    } else //0b. Ah, we already got the ID from the DB.
      this.id
  }

  private def setName(name:String) = this.name = name

  private def setPlayerUID(playerUID:String) = this.playerUID = playerUID

  private def setID = this.id = getID


}
