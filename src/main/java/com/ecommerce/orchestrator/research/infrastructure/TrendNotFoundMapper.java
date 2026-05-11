package com.ecommerce.orchestrator.research.infrastructure;

import com.ecommerce.orchestrator.research.domain.TrendNotFound;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TrendNotFoundMapper implements ExceptionMapper<TrendNotFound> {

    @Override
    public Response toResponse(TrendNotFound exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
