package com.ecommerce.orchestrator.shared.infrastructure.bus.command;

import com.ecommerce.orchestrator.shared.domain.bus.command.Command;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandBus;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandHandler;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandHandlerExecutionError;
import com.ecommerce.orchestrator.shared.domain.bus.command.CommandNotRegisteredError;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;

@ApplicationScoped
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;

    public InMemoryCommandBus() {
        this.information = null;
    }

    @Inject
    public InMemoryCommandBus(CommandHandlersInformation information) {
        this.information = information;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {
            Class<? extends CommandHandler> handlerClass = information.search(command.getClass());
            CommandHandler handler = CDI.current().select(handlerClass).get();
            handler.handle(command);
        } catch (CommandNotRegisteredError error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
