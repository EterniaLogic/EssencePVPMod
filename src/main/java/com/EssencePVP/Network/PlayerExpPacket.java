package com.EssencePVP.Network;

import com.EssencePVP.Player.Player;

import net.minecraft.client.Minecraft;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


public class PlayerExpPacket implements IMessageHandler<PlayerExpMessage, IMessage>
{
	@Override
	public IMessage onMessage(PlayerExpMessage message, MessageContext ctx) {
		return null;
	}
}

class PlayerExpMessage implements IMessage{
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeBytes(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes()); // Send player name
		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// set from server
		Player p = ((Player)Player.getPlayerMap().get(Minecraft.getMinecraft().thePlayer));
		p.getExp().setTotalexp(buf.readFloat()); // Set current Exp
	}
}