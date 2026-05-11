package com.ecommerce.orchestrator.research.application;

import java.util.List;

import com.ecommerce.orchestrator.shared.domain.bus.query.Response;

public record TrendProductsResponse(List<TrendProductResponse> products) implements Response {}
