package com.ecommerce.orchestrator.research.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(configKey = "serpapi")
@Path("/search.json")
public interface SerpApiClient {

    @GET
    @Produces("application/json")
    JsonNode search(
            @QueryParam("engine") String engine,
            @QueryParam("q") String query,
            @QueryParam("gl") String country,
            @QueryParam("hl") String hl,
            @QueryParam("google_domain") String googleDomain,
            @QueryParam("api_key") String apiKey);
}
