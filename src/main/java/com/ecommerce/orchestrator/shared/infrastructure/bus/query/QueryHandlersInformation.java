package com.ecommerce.orchestrator.shared.infrastructure.bus.query;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.orchestrator.shared.domain.bus.query.Query;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryHandler;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryNotRegisteredError;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public final class QueryHandlersInformation {
    private final Map<Class<? extends Query>, Class<? extends QueryHandler>> indexedQueryHandlers;

    public QueryHandlersInformation() {
        this.indexedQueryHandlers = new HashMap<>();
    }

    @Inject
    void indexHandlers(Instance<QueryHandler> handlers) {
        for (QueryHandler handler : handlers) {
            for (var genericInterface : handler.getClass().getGenericInterfaces()) {
                if (genericInterface instanceof ParameterizedType paramType
                        && QueryHandler.class.equals(paramType.getRawType())) {
                    Class<? extends Query> queryClass =
                            (Class<? extends Query>) paramType.getActualTypeArguments()[0];
                    indexedQueryHandlers.put(
                            queryClass, (Class<? extends QueryHandler>) handler.getClass());
                }
            }
        }
    }

    public Class<? extends QueryHandler> search(Class<? extends Query> queryClass)
            throws QueryNotRegisteredError {
        Class<? extends QueryHandler> handlerClass = indexedQueryHandlers.get(queryClass);
        if (null == handlerClass) {
            throw new QueryNotRegisteredError(queryClass);
        }
        return handlerClass;
    }
}
