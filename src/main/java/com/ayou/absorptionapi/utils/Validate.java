package com.ayou.absorptionapi.utils;

import java.util.*;

public class Validate {
    public static <O> O notNull(final O value, final String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static String notEmpty(final String value, final String message) {
        notNull(value, message);
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <O, C extends Collection<O>> C notEmpty(final C value, final String message) {
        notNull(value, message);
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
}
