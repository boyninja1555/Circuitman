package com.boyninja1555.circuitman.commands.lib;

import com.boyninja1555.circuitman.Circuitman;

public abstract class CCommand {
    public final Circuitman plugin;

    public CCommand(Circuitman plugin) {
        this.plugin = plugin;
    }
}
