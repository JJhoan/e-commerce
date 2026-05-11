package com.ecommerce.orchestrator.shared.domain;

import java.util.Objects;

public final class CountryCode {

    public enum Supported {
        MX,
        CO,
        AR,
        BR,
        CL,
        PE
    }

    private final String code;

    private CountryCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Country code cannot be null or blank");
        }
        if (!code.matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException(
                    "Country code must be 2 uppercase letters (ISO 3166-1 alpha-2): " + code);
        }
        if (!isValid(code)) {
            throw new IllegalArgumentException("Unsupported country: " + code);
        }
        this.code = code;
    }

    public static CountryCode of(String code) {
        return new CountryCode(code);
    }

    public static CountryCode from(Supported supported) {
        return new CountryCode(supported.name());
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
        CountryCode that = (CountryCode) o;
        return code.equals(that.code);
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
