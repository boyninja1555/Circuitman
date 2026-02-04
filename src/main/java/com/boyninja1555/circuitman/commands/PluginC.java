package com.boyninja1555.circuitman.commands;

import com.boyninja1555.circuitman.Circuitman;
import com.boyninja1555.circuitman.commands.lib.CCommand;
import com.boyninja1555.circuitman.commands.lib.PluginCAction;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

import java.io.File;

public class PluginC extends CCommand {

    public PluginC(Circuitman plugin) {
        super(plugin);
    }

    @Command("circuitman <action>")
    @CommandDescription("Manages the Circuitman plugin")
    @Permission("circuitman.commands.circuitman")
    public void plugin(
            CommandSourceStack source,
            @Argument(value = "action") PluginCAction action
    ) {
        CommandSender sender = source.getSender();

        switch (action) {
            case RELOAD -> {
                plugin.config.reload();
                sender.sendMessage(plugin.config.adminMessages.get("reloaded"));
            }

            case REGENERATE_CONFIG -> {
                File file = new File(plugin.getDataFolder(), "config.yml");

                if (file.exists() || file.isDirectory()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        plugin.getLogger().severe("Could not regenerate config! Please ensure you can access the " + file.getParentFile() + " directory");
                        sender.sendMessage(plugin.config.adminMessages.get("config-not-regenerated"));
                        return;
                    }
                }

                plugin.saveDefaultConfig();
                plugin.config.reload();
                sender.sendMessage(plugin.config.adminMessages.get("config-regenerated"));
            }
        }
    }
}
