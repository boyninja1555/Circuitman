package com.boyninja1555.circuitman;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class CircuitConfig {
    private final Circuitman plugin;

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
        redstoneBurnTime = config.getLong("redstone-burn-time");
        redstoneFlameCount = config.getInt("redstone-flame-count");
        redstoneTntExplodePower = (float) config.getDouble("redstone-tnt-explode-power");
        redstoneTntExplodeFire = config.getBoolean("redstone-tnt-explode-fire");
        redstoneBurnDepth = config.getInt("redstone-burn-depth");
        experimentalMode = config.getBoolean("experimental-mode");
        loadExperimental();
    }

    public void loadExperimental() {
        if (!experimentalMode)
            return;

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
