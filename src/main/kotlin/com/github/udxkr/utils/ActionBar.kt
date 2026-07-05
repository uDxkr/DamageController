package com.github.udxkr.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
object ActionBar {

    private val serverVersion: String = Bukkit.getServer().javaClass.`package`.name
        .substringAfterLast('.')

    fun sendToPlayer(player: Player, message: String) {
        try {
            val craftPlayerClass = Class.forName("org.bukkit.craftbukkit.$serverVersion.entity.CraftPlayer")
            val entityPlayer = craftPlayerClass.getMethod("getHandle").invoke(player)

            val playerConnection = entityPlayer.javaClass.getField("playerConnection").get(entityPlayer)

            val iChatBaseComponentClass = Class.forName("net.minecraft.server.$serverVersion.IChatBaseComponent")
            val chatComponentTextClass = Class.forName("net.minecraft.server.$serverVersion.ChatComponentText")
            val chatComponentText = chatComponentTextClass
                .getConstructor(String::class.java)
                .newInstance(message)

            val packetClass = Class.forName("net.minecraft.server.$serverVersion.PacketPlayOutChat")
            val packet = packetClass
                .getConstructor(iChatBaseComponentClass, Byte::class.javaPrimitiveType)
                .newInstance(chatComponentText, 2.toByte())

            val packetNmsClass = Class.forName("net.minecraft.server.$serverVersion.Packet")
            playerConnection.javaClass
                .getMethod("sendPacket", packetNmsClass)
                .invoke(playerConnection, packet)

        } catch (e: Exception) {
            player.sendMessage(message)
        }
    }
}