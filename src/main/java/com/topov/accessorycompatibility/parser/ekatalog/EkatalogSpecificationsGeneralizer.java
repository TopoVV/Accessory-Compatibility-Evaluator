package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsGeneralizer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class EkatalogSpecificationsGeneralizer implements SpecificationsGeneralizer {
    private final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";

    private final Map<String, String> keys = new HashMap<>();

    @PostConstruct
    public void configureKeys() {
        keys.put("Socket", SOCKET_KEY);
        keys.put("Разъем (Socket)", SOCKET_KEY);
        keys.put("Тепловыделение (TDP)", TDP_KEY);
        keys.put("Тактовая частота", GHZ_KEY);
    }

    @Override
    public Map<String, String> generalizeSpecifications(Map<String, String> specifications) {
        final Map<String, String> generalized = new HashMap<>();
        specifications.keySet().forEach(key -> {
            final String appropriateKey = this.keys.get(key);
            final String value = specifications.get(key).replaceAll(CYRILLIC, "").trim();
            generalized.put(appropriateKey, value.equals("") ? "true" : value);
        });
        return generalized;
    }
}
