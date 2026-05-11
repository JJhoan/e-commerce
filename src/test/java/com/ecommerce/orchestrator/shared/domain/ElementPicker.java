package com.ecommerce.orchestrator.shared.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ElementPicker {

    private ElementPicker() {}

    public static <T> T from(T... elements) {
        List<T> list = Arrays.asList(elements);
        return from(list);
    }

    public static <T> T from(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Cannot pick from empty collection");
        }
        int index = ThreadLocalRandom.current().nextInt(elements.size());
        return elements.get(index);
    }

    public static int between(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double between(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
