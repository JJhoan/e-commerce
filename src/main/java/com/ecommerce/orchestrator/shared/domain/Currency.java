package com.ecommerce.orchestrator.shared.domain;

import java.util.Objects;

public final class Currency {

    public enum Supported {
        USD,
        MXN,
        ARS,
        COP,
        BRL,
        CLP,
        PEN
    }

    private final String code;

    private Currency(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Currency code cannot be null or blank");
        }
        if (!code.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException(
                    "Currency code must be 3 uppercase letters: " + code);
        }
        if (!isValid(code)) {
            throw new IllegalArgumentException("Unsupported currency: " + code);
        }
        this.code = code;
    }

    public static Currency of(String code) {
        return new Currency(code);
    }

    public static Currency from(Supported supported) {
        return new Currency(supported.name());
    }

    private static boolean isValid(String code) {
        for (Supported s : Supported.values()) {
            if (s.name().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public String code() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}
