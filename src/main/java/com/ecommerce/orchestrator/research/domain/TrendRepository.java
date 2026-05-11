package com.ecommerce.orchestrator.research.domain;

import java.util.List;

public interface TrendRepository {
    List<TrendProduct> search(TrendQuery query);
}
