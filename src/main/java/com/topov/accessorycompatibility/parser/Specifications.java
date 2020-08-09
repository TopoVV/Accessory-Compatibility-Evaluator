package com.topov.accessorycompatibility.parser;

import java.util.Map;

public class Specifications {
    private final Map<String, String> specifications;

    public Specifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    public String getStringValue(String key) {
        return specifications.getOrDefault(key, "undefined");
    }

    public String getNumberValue(String key) {
        final String value = specifications.getOrDefault(key, "-100").replaceAll("\\D+", "");
        return value.isEmpty() ? "-100" : value;
    }
}
