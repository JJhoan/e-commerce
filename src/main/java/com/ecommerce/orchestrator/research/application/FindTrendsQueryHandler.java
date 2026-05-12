package com.ecommerce.orchestrator.research.application;

import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendQuery;
import com.ecommerce.orchestrator.shared.domain.CountryCode;
import com.ecommerce.orchestrator.shared.domain.bus.query.QueryHandler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindTrendsQueryHandler
        implements QueryHandler<FindTrendsQuery, TrendProductsResponse> {

    private final TrendFinder finder;

    @Inject
    public FindTrendsQueryHandler(TrendFinder finder) {
        this.finder = finder;
    }

    @Override
    public TrendProductsResponse handle(FindTrendsQuery query) {
        TrendQuery trendQuery =
                new TrendQuery(query.searchQuery(), CountryCode.of(query.country()));
        List<TrendProduct> products = finder.find(trendQuery);
        List<TrendProductResponse> responses = products.stream().map(this::mapToResponse).toList();
        return new TrendProductsResponse(responses);
    }

    private TrendProductResponse mapToResponse(TrendProduct product) {
        return new TrendProductResponse(
                product.title(),
                product.price(),
                product.currency(),
                product.rating(),
                product.reviews(),
                product.source(),
                product.thumbnail(),
                product.tag());
    }
}
