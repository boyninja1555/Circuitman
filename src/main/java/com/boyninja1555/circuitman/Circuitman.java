package com.boyninja1555.circuitman;

import org.bukkit.plugin.java.JavaPlugin;

public final class Circuitman extends JavaPlugin {
    public boolean isFolia = false;
    public CircuitServices services;

    @Override
    public void onEnable() {
        isFolia = getServer().getName().equals("Folia");
        services = new CircuitServices(this);
    }
}
