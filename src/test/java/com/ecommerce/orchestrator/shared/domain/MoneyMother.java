package com.ecommerce.orchestrator.shared.domain;

import java.math.BigDecimal;

public final class MoneyMother {

    private MoneyMother() {}

    public static Money random() {
        return Money.of(ElementPicker.between(1, 10000), CurrencyMother.random());
    }

    public static Money of(double amount) {
        return Money.of(amount, CurrencyMother.usd());
    }

    public static Money of(double amount, Currency currency) {
        return Money.of(amount, currency);
    }

    public static Money usd(double amount) {
        return Money.of(amount, CurrencyMother.usd());
    }

    public static Money mxn(double amount) {
        return Money.of(amount, CurrencyMother.mxn());
    }

    public static Money ars(double amount) {
        return Money.of(amount, CurrencyMother.ars());
    }

    public static Money cop(double amount) {
        return Money.of(amount, CurrencyMother.cop());
    }

    public static Money brl(double amount) {
        return Money.of(amount, CurrencyMother.brl());
    }

    public static Money zero() {
        return Money.zero(CurrencyMother.usd());
    }

    public static Money withAmount(BigDecimal amount) {
        return Money.of(amount, CurrencyMother.usd());
    }
}
