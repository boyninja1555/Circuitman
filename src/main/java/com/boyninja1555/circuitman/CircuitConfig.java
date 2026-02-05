package com.boyninja1555.circuitman;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CircuitConfig {
    public static final Component DEFAULT_ADMIN_MESSAGE = Component.text("Circuitman config.yml missing \"admin\" section for admin messages", NamedTextColor.RED);
    private final Circuitman plugin;

    public Map<String, Component> adminMessages;
    public long redstoneBurnTime;
    public int redstoneFlameCount;
    public float redstoneTntExplodePower;
    public boolean redstoneTntExplodeFire;
    public int redstoneBurnDepth;
    public boolean experimentalMode;

    public CircuitConfig(Circuitman plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        plugin.reloadConfig();
        load();
    }

    public void load() {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection adminMessagesSection = config.getConfigurationSection("admin");

        if (adminMessages == null) adminMessages = new HashMap<>();
        if (adminMessagesSection == null)
            plugin.getLogger().warning("Config.yml missing \"admin\" section for admin messages! An error message will be shown to admins instead.");
        else for (String key : adminMessagesSection.getKeys(false)) {
            Component value = adminMessagesSection.getRichMessage(key, DEFAULT_ADMIN_MESSAGE);
            adminMessages.put(key, value);
        }

        redstoneBurnTime = config.getLong("redstone-burn-time");
        redstoneFlameCount = config.getInt("redstone-flame-count");
        redstoneTntExplodePower = (float) config.getDouble("redstone-tnt-explode-power");
        redstoneTntExplodeFire = config.getBoolean("redstone-tnt-explode-fire");
        redstoneBurnDepth = config.getInt("redstone-burn-depth");
        experimentalMode = config.getBoolean("experimental-mode");
        loadExperimental();
    }

    public void loadExperimental() {
        if (!experimentalMode) return;

        redstoneBurnTime = 2L;
        redstoneFlameCount = 10;
        redstoneTntExplodePower = 45;
        redstoneTntExplodeFire = true;
        redstoneBurnDepth = 100;
    }

    public void save() {
        try {
            FileConfiguration config = plugin.getConfig();
            config.set("redstone-burn-time", redstoneBurnTime);
            config.set("redstone-flame-count", redstoneFlameCount);
            config.set("redstone-tnt-explode-power", redstoneTntExplodePower);
            config.set("redstone-tnt-explode-fire", redstoneTntExplodeFire);
            config.set("redstone-burn-depth", redstoneBurnDepth);
            config.set("experimental-mode", experimentalMode);
            config.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            plugin.getLogger().severe("Could not save config! " + ex.getMessage());
        }
    }
}
