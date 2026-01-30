package com.boyninja1555.circuitman.listeners;

import com.boyninja1555.circuitman.Circuitman;
import com.boyninja1555.circuitman.listeners.lib.CListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class FireLightL extends CListener {

    public FireLightL(Circuitman plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (!block.getType().equals(Material.FIRE))
            return;

        plugin.services.gunpowderBurner.forBlock(block);
    }

    @EventHandler
    public void onFireIgnite(BlockIgniteEvent event) {
        Block block = event.getBlock();

        if (!block.getType().equals(Material.FIRE))
            return;

        plugin.services.gunpowderBurner.forBlock(block);
    }
}
