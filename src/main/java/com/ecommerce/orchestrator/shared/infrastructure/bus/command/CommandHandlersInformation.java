package com.ecommerce.orchestrator.shared.infrastructure.bus.command;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.orchestrator.shared.domain.bus.command.Command;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandHandler;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandNotRegisteredError;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public final class CommandHandlersInformation {
    private final Map<Class<? extends Command>, Class<? extends CommandHandler>>
            indexedCommandHandlers;

    public CommandHandlersInformation() {
        this.indexedCommandHandlers = new HashMap<>();
    }

    @Inject
    void indexHandlers(Instance<CommandHandler> handlers) {
        for (CommandHandler handler : handlers) {
            for (var genericInterface : handler.getClass().getGenericInterfaces()) {
                if (genericInterface instanceof ParameterizedType paramType
                        && CommandHandler.class.equals(paramType.getRawType())) {
                    Class<? extends Command> commandClass =
                            (Class<? extends Command>) paramType.getActualTypeArguments()[0];
                    indexedCommandHandlers.put(
                            commandClass, (Class<? extends CommandHandler>) handler.getClass());
                }
            }
        }
    }

    public Class<? extends CommandHandler> search(Class<? extends Command> commandClass)
            throws CommandNotRegisteredError {
        Class<? extends CommandHandler> handlerClass = indexedCommandHandlers.get(commandClass);
        if (null == handlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }
        return handlerClass;
    }
}
