package com.ecommerce.orchestrator.shared.domain;

public final class CountryCodeMother {

    private CountryCodeMother() {}

    public static CountryCode random() {
        return ElementPicker.from(mexico(), colombia(), argentina(), brazil(), chile(), peru());
    }

    public static CountryCode mexico() {
        return CountryCode.of("MX");
    }

    public static CountryCode colombia() {
        return CountryCode.of("CO");
    }

    public static CountryCode argentina() {
        return CountryCode.of("AR");
    }

    public static CountryCode brazil() {
        return CountryCode.of("BR");
    }

    public static CountryCode chile() {
        return CountryCode.of("CL");
    }

    public static CountryCode peru() {
        return CountryCode.of("PE");
    }
}
