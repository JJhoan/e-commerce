package com.ecommerce.orchestrator.research.application;

import com.ecommerce.orchestrator.shared.domain.bus.query.Query;

public record FindTrendsQuery(String country, String searchQuery) implements Query {}
