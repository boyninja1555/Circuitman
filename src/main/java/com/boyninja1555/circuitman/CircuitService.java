package com.boyninja1555.circuitman;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public abstract class CircuitService {
    public final Circuitman plugin;

    public CircuitService(Circuitman plugin) {
        this.plugin = plugin;
    }

    @ApiStatus.OverrideOnly
    public abstract @NotNull CircuitService registerService();
}
