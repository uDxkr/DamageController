package com.github.udxkr

import co.aikar.commands.BukkitCommandManager
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import com.github.udxkr.commands.DamageCommand
import com.github.udxkr.listener.DamageManager
import java.io.File

class DamageController : JavaPlugin() {

    companion object {
        lateinit var instance: DamageController
            private set
    }

    private lateinit var damageConfigFile: File
    lateinit var damageConfig: FileConfiguration
        private set

    override fun onEnable() {
        instance = this

        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }

        setupDamageConfig()

        server.pluginManager.registerEvents(DamageManager(), this)

        val commandManager = BukkitCommandManager(this)
        commandManager.registerCommand(DamageCommand())
        logger.info("\u001B[32m[DamageController] Plugin ativado com sucesso!\u001B[0m");
        logger.info("\u001B[32m[DamageController] Versao 1.0\u001B[0m");
        logger.info("\u001B[32m[DamageController] Developed By Dxkr\u001B[0m");
        logger.info("\u001B[32m[DamageController] github.com/uDxkr!\u001B[0m");
    }

    override fun onDisable() {
        saveDamageConfig()
        logger.info("\u001B[31m[DamageController] Plugin ativado com sucesso!\u001B[0m");
        logger.info("\u001B[31m[DamageController] Versao 1.0\u001B[0m");
        logger.info("\u001B[31m[DamageController] Developed By Dxkr\u001B[0m");
        logger.info("\u001B[31m[DamageController] github.com/uDxkr!\u001B[0m");
    }

    private fun setupDamageConfig() {
        damageConfigFile = File(dataFolder, "damage.yml")

        if (!damageConfigFile.exists()) {
            try {
                damageConfigFile.parentFile.mkdirs()
                damageConfigFile.createNewFile()
            } catch (e: Exception) {
                logger.severe("Não foi possível criar damage.yml: ${e.message}")
            }
        }

        damageConfig = YamlConfiguration.loadConfiguration(damageConfigFile)

        if (!damageConfig.contains("Damage.Multiplier")) {
            damageConfig.set("Damage.Multiplier", 1.0)
            saveDamageConfig()
        }
    }

    fun saveDamageConfig() {
        try {
            damageConfig.save(damageConfigFile)
        } catch (e: Exception) {
            logger.severe("Não foi possível guardar damage.yml: ${e.message}")
        }
    }
}
