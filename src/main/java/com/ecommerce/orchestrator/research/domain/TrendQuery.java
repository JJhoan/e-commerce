package com.ecommerce.orchestrator.research.domain;

import com.ecommerce.orchestrator.shared.domain.CountryCode;

public final class TrendQuery {

    private final String query;
    private final CountryCode country;

    public TrendQuery(String query, CountryCode country) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Query cannot be null or blank");
        }
        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null");
        }
        this.query = query;
        this.country = country;
    }

    public String query() {
        return query;
    }

    public CountryCode country() {
        return country;
    }
}
