package com.ecommerce.orchestrator.research.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ecommerce.orchestrator.shared.domain.CountryCode;
import com.ecommerce.orchestrator.shared.domain.CountryCodeMother;
import com.ecommerce.orchestrator.shared.domain.ElementPicker;

public final class TrendMother {

    private TrendMother() {}

    public static TrendProduct random() {
        return new TrendProduct(
                ElementPicker.from("Xiaomi Redmi A5", "iPhone 15", "Samsung Galaxy S24"),
                BigDecimal.valueOf(ElementPicker.between(100, 50000)),
                ElementPicker.from("MXN", "USD", "ARS", "COP", "BRL", "CLP", "PEN"),
                BigDecimal.valueOf(ElementPicker.between(1.0, 5.0)),
                ElementPicker.between(0, 50000),
                ElementPicker.from("Amazon", "Mercado Libre", "Chedraui", "Walmart"),
                "https://example.com/thumbnail.jpg",
                UUID.randomUUID().toString(),
                ElementPicker.from("OFERTA", null));
    }

    public static TrendProduct withCountry(CountryCode country) {
        return random();
    }

    public static List<TrendProduct> randomList() {
        int size = ElementPicker.between(1, 5);
        List<TrendProduct> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random());
        }
        return list;
    }

    public static TrendQuery randomQuery() {
        return new TrendQuery(
                ElementPicker.from("phones", "shoes", "laptops", "headphones"),
                CountryCodeMother.random());
    }
}
