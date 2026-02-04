package com.boyninja1555.circuitman.services;

import com.boyninja1555.circuitman.CircuitService;
import com.boyninja1555.circuitman.Circuitman;
import com.boyninja1555.circuitman.commands.lib.CCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CircuitCommands extends CircuitService {
    private AnnotationParser<CommandSourceStack> parser;

    public CircuitCommands(Circuitman plugin) {
        super(plugin);
    }

    @Override
    public @NotNull CircuitCommands registerService() {
        ExecutionCoordinator<CommandSourceStack> executionCoordinator = ExecutionCoordinator.simpleCoordinator();
        PaperCommandManager<CommandSourceStack> manager = PaperCommandManager.builder().executionCoordinator(executionCoordinator).buildOnEnable(plugin);
        parser = new AnnotationParser<>(manager, CommandSourceStack.class);
        return this;
    }

    public void registerCommand(Class<? extends CCommand> command) {
        try {
            parser.parse(command.getConstructor(Circuitman.class).newInstance(plugin));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
            plugin.getLogger().warning("Could not register a command! " + ex.getMessage());
        }
    }
}
