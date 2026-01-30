package com.boyninja1555.circuitman.services;

import com.boyninja1555.circuitman.CircuitService;
import com.boyninja1555.circuitman.Circuitman;
import com.boyninja1555.circuitman.listeners.lib.CListener;
import org.jetbrains.annotations.NotNull;

public class CircuitListeners extends CircuitService {

    public CircuitListeners(Circuitman plugin) {
        super(plugin);
    }

    @Override
    public @NotNull CircuitListeners registerService() {
        return this;
    }

    public void register(Class<? extends CListener> listener) {
        try {
            listener.getConstructor(Circuitman.class)
                    .newInstance(plugin)
                    .register();
        } catch (Exception ex) {
            plugin.getLogger().warning("Could not register listener! " + ex.getMessage());
        }
    }
}
