package com.ecommerce.orchestrator.research.infrastructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendQuery;
import com.ecommerce.orchestrator.research.domain.TrendRepository;
import com.fasterxml.jackson.databind.JsonNode;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SerpApiTrendRepository implements TrendRepository {

    private final SerpApiClient client;
    private final String apiKey;

    @Inject
    public SerpApiTrendRepository(
            @RestClient SerpApiClient client, @ConfigProperty(name = "serpapi.key") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    @Override
    public List<TrendProduct> search(TrendQuery query) {
        String countryCode = query.country().code().toLowerCase();
        String googleDomain = "google.com." + countryCode;

        JsonNode response =
                client.search(
                        "google_shopping", query.query(), countryCode, "es", googleDomain, apiKey);

        JsonNode shoppingResults = response.get("shopping_results");
        if (shoppingResults == null || !shoppingResults.isArray()) {
            return Collections.emptyList();
        }

        List<TrendProduct> products = new ArrayList<>();
        for (JsonNode node : shoppingResults) {
            products.add(mapNode(node));
        }
        return products;
    }

    private TrendProduct mapNode(JsonNode result) {
        String title = textOrEmpty(result, "title");
        BigDecimal price = extractedPrice(result);
        String currency = extractCurrency(textOrEmpty(result, "price"));
        BigDecimal rating = decimalOrZero(result, "rating");
        int reviews = intOrZero(result, "reviews");
        String source = textOrEmpty(result, "source");
        String thumbnail = textOrEmpty(result, "thumbnail");
        String productId = textOrEmpty(result, "product_id");
        String tag = textOrNull(result, "tag");

        return new TrendProduct(
                title, price, currency, rating, reviews, source, thumbnail, productId, tag);
    }

    private String textOrEmpty(JsonNode node, String field) {
        JsonNode value = node.get(field);
        return value != null && !value.isNull() ? value.asText() : "";
    }

    private String textOrNull(JsonNode node, String field) {
        JsonNode value = node.get(field);
        return value != null && !value.isNull() ? value.asText() : null;
    }

    private BigDecimal extractedPrice(JsonNode node) {
        JsonNode value = node.get("extracted_price");
        if (value != null && !value.isNull() && value.isNumber()) {
            return BigDecimal.valueOf(value.asDouble());
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal decimalOrZero(JsonNode node, String field) {
        JsonNode value = node.get(field);
        if (value != null && !value.isNull() && value.isNumber()) {
            return BigDecimal.valueOf(value.asDouble());
        }
        return BigDecimal.ZERO;
    }

    private int intOrZero(JsonNode node, String field) {
        JsonNode value = node.get(field);
        if (value != null && !value.isNull() && value.isNumber()) {
            return value.asInt();
        }
        return 0;
    }

    private String extractCurrency(String priceString) {
        if (priceString == null || priceString.isBlank()) {
            return "";
        }
        return priceString.replaceAll("[0-9.,\\s]", "").trim();
    }
}
