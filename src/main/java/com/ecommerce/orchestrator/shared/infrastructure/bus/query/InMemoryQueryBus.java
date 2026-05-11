package com.ecommerce.orchestrator.shared.infrastructure.bus.query;

import com.ecommerce.orchestrator.shared.domain.bus.query.Query;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryBus;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryHandler;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryHandlerExecutionError;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryNotRegisteredError;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;

@ApplicationScoped
public final class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;

    public InMemoryQueryBus() {
        this.information = null;
    }

    @Inject
    public InMemoryQueryBus(QueryHandlersInformation information) {
        this.information = information;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R ask(Query query) throws QueryHandlerExecutionError {
        try {
            Class<? extends QueryHandler> handlerClass = information.search(query.getClass());
            QueryHandler handler = CDI.current().select(handlerClass).get();
            return (R) handler.handle(query);
        } catch (QueryNotRegisteredError error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}
