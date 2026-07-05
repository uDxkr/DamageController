package com.github.udxkr.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import co.aikar.commands.annotation.Syntax
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import com.github.udxkr.DamageController
import com.github.udxkr.utils.ActionBar

@CommandAlias("damage")
@CommandPermission("damagecontroller.value")
class DamageCommand : BaseCommand() {

    companion object {
        private const val MIN_MULTIPLIER = 0.01
        private const val MAX_MULTIPLIER = 40.0
    }

    @Default
    @Syntax("<valor>")
    fun onDamage(sender: CommandSender, valorStr: String) {
        val multiplier = valorStr.toDoubleOrNull()
        if (multiplier == null) {
            sender.sendMessage(ChatColor.RED.toString() + "Por favor, insira um valor numérico.")
            return
        }

        if (multiplier !in MIN_MULTIPLIER..MAX_MULTIPLIER) {
            sender.sendMessage(
                ChatColor.RED.toString() + "O valor do dano tem de estar entre "
                    + MIN_MULTIPLIER + " e " + MAX_MULTIPLIER + "."
            )
            return
        }

        DamageController.instance.damageConfig.set("Damage.Multiplier", multiplier)
        DamageController.instance.saveDamageConfig()

        sender.sendMessage(ChatColor.GREEN.toString() + "Dano configurado para: $multiplier")

        val actionBarMsg = ChatColor.RED.toString() + "Dano alterado para: " + ChatColor.YELLOW + multiplier
        for (player in Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("damagecontroller.value")) {
                ActionBar.sendToPlayer(player, actionBarMsg)
            }
        }
    }
    @Subcommand("info")
    fun onInfo(sender: CommandSender) {
        val multiplier = DamageController.instance.damageConfig.getDouble("Damage.Multiplier", 1.0)

        sender.sendMessage(ChatColor.YELLOW.toString() + "Dano atual: " + ChatColor.GREEN + multiplier)
    }
}
