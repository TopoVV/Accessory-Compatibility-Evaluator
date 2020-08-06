package com.topov.accessorycompatibility.parser;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpecificationKeyGeneralizer {
    private final Map<String, String> keys = new HashMap<>();

    @PostConstruct
    public void configureKeys() {
        keys.put("Разъем (Socket)", "socket");
        keys.put("Тепловыделение (TDP)", "tdp");
        keys.put("Тактовая частота", "ghz default");
    }

    public String getAppropriateKey(String key) {
        return keys.getOrDefault(key, key);
    }
}
