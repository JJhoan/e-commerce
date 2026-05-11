package com.ecommerce.orchestrator.shared.domain;

public final class CurrencyMother {

    private CurrencyMother() {}

    public static Currency random() {
        return ElementPicker.from(usd(), mxn(), ars(), cop(), brl(), clp(), pen());
    }

    public static Currency usd() {
        return Currency.of("USD");
    }

    public static Currency mxn() {
        return Currency.of("MXN");
    }

    public static Currency ars() {
        return Currency.of("ARS");
    }

    public static Currency cop() {
        return Currency.of("COP");
    }

    public static Currency brl() {
        return Currency.of("BRL");
    }

    public static Currency clp() {
        return Currency.of("CLP");
    }

    public static Currency pen() {
        return Currency.of("PEN");
    }
}
