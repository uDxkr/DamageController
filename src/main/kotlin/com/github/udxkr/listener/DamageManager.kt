package com.github.udxkr.listener

import com.github.udxkr.DamageController
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageManager : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onDamage(event: EntityDamageByEntityEvent) {
        val damager = event.damager as? Player ?: return
        if (event.entity !is Player) return

        val weapon = damager.itemInHand

        if (weapon.type != Material.DIAMOND_SWORD && weapon.type != Material.DIAMOND_AXE) return

        val baseDamage = event.damage
        var multiplier = DamageController.instance.damageConfig.getDouble("Damage.Multiplier", 1.0)
        if (multiplier <= 0) {
            multiplier = 1.0
        }

        event.damage = baseDamage * multiplier
    }
}
