package com.boyninja1555.circuitman;

import com.boyninja1555.circuitman.commands.PluginC;
import com.boyninja1555.circuitman.listeners.FireLightL;
import com.boyninja1555.circuitman.services.CircuitCommands;
import com.boyninja1555.circuitman.services.CircuitListeners;
import com.boyninja1555.circuitman.services.RedstoneBurner;

public class CircuitServices {
    public final RedstoneBurner gunpowderBurner;
    public final CircuitListeners listeners;
    public final CircuitCommands commands;

    public CircuitServices(Circuitman plugin) {
        gunpowderBurner = new RedstoneBurner(plugin).registerService();

        listeners = new CircuitListeners(plugin).registerService();
        listeners.register(FireLightL.class);

        commands = new CircuitCommands(plugin).registerService();
        commands.registerCommand(PluginC.class);
    }
}
