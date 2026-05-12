package com.ecommerce.orchestrator.research.application;

import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendNotFound;
import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendQuery;
import com.ecommerce.orchestrator.research.domain.TrendRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TrendFinder {

    private final TrendRepository repository;

    @Inject
    public TrendFinder(TrendRepository repository) {
        this.repository = repository;
    }

    public List<TrendProduct> find(TrendQuery query) {
        List<TrendProduct> products = repository.search(query);
        if (products.isEmpty()) {
            throw new TrendNotFound("No trends found for query: " + query.query());
        }
        return products;
    }
}
