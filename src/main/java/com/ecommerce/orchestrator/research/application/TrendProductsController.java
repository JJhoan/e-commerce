package com.ecommerce.orchestrator.research.application;

import com.ecommerce.orchestrator.shared.domain.bus.query.QueryBus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/research/trends")
public class TrendProductsController {

    private final QueryBus bus;

    @Inject
    public TrendProductsController(QueryBus bus) {
        this.bus = bus;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrendProductsResponse getTrends(
            @QueryParam("country") String country, @QueryParam("query") String query) {
        return bus.ask(new FindTrendsQuery(country, query));
    }
}
