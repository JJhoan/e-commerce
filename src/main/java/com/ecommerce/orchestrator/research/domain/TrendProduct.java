package com.ecommerce.orchestrator.research.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class TrendProduct {

    private final String title;
    private final BigDecimal price;
    private final String currency;
    private final BigDecimal rating;
    private final int reviews;
    private final String source;
    private final String thumbnail;
    private final String productId;
    private final String tag;

    public TrendProduct(
            String title,
            BigDecimal price,
            String currency,
            BigDecimal rating,
            int reviews,
            String source,
            String thumbnail,
            String productId,
            String tag) {
        this.title = title;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.reviews = reviews;
        this.source = source;
        this.thumbnail = thumbnail;
        this.productId = productId;
        this.tag = tag;
    }

    public String title() {
        return title;
    }

    public BigDecimal price() {
        return price;
    }

    public String currency() {
        return currency;
    }

    public BigDecimal rating() {
        return rating;
    }

    public int reviews() {
        return reviews;
    }

    public String source() {
        return source;
    }

    public String thumbnail() {
        return thumbnail;
    }

    public String productId() {
        return productId;
    }

    public String tag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrendProduct that = (TrendProduct) o;
        return reviews == that.reviews
                && Objects.equals(title, that.title)
                && Objects.equals(price, that.price)
                && Objects.equals(currency, that.currency)
                && Objects.equals(rating, that.rating)
                && Objects.equals(source, that.source)
                && Objects.equals(thumbnail, that.thumbnail)
                && Objects.equals(productId, that.productId)
                && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                title, price, currency, rating, reviews, source, thumbnail, productId, tag);
    }
}
