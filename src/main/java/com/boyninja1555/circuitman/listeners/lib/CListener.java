package com.boyninja1555.circuitman.listeners.lib;

import com.boyninja1555.circuitman.Circuitman;
import org.bukkit.event.Listener;

public class CListener implements Listener {
    public final Circuitman plugin;

    public CListener(Circuitman plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
