package com.ecommerce.orchestrator.research.application;

import java.math.BigDecimal;

public record TrendProductResponse(
        String title,
        BigDecimal price,
        String currency,
        BigDecimal rating,
        int reviews,
        String source,
        String thumbnail,
        String tag) {}
