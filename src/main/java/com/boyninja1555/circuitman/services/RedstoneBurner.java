package com.boyninja1555.circuitman.services;

import com.boyninja1555.circuitman.CircuitService;
import com.boyninja1555.circuitman.Circuitman;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class RedstoneBurner extends CircuitService {

    public RedstoneBurner(Circuitman plugin) {
        super(plugin);
    }

    @Override
    public @NotNull RedstoneBurner registerService() {
        return this;
    }

    public void explode(Block block) {
        World world = block.getWorld();
        Location loc = block.getLocation();
        block.setType(Material.AIR);
        world.createExplosion(loc, plugin.config.redstoneTntExplodePower, plugin.config.redstoneTntExplodeFire);
    }

    public void forBlock(Block block) {
        forBlock(plugin.config.redstoneBurnDepth, block);
    }

    public void forBlock(int depth, Block block) {
        if (depth <= 0)
            return;

        World world = block.getWorld();
        Location loc = block.getLocation();
        Block forward = world.getBlockAt(loc.clone().add(0, 0, 1));
        Block back = world.getBlockAt(loc.clone().add(0, 0, -1));
        Block right = world.getBlockAt(loc.clone().add(1, 0, 0));
        Block left = world.getBlockAt(loc.clone().add(-1, 0, 0));

        if (block.getType().equals(Material.REDSTONE_WIRE))
            block.setType(Material.AIR);

        world.spawnParticle(Particle.FLAME, block.getLocation(), plugin.config.redstoneFlameCount);
        world.playSound(block.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1f, 1f);

        if (plugin.isFolia)
            plugin.getServer().getRegionScheduler().runDelayed(plugin, loc, task -> {
                check(forward, depth);
                check(back, depth);
                check(right, depth);
                check(left, depth);
            }, plugin.config.redstoneBurnTime);
        else
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                check(forward, depth);
                check(back, depth);
                check(right, depth);
                check(left, depth);
            }, plugin.config.redstoneBurnTime);
    }

    private void check(Block target, int depth) {
        if (target.getType() == Material.REDSTONE_WIRE)
            forBlock(depth - 1, target);
        else if (target.getType() == Material.TNT)
            explode(target);
    }
}
