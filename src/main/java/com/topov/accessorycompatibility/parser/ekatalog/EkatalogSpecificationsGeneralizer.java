package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsGeneralizer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class EkatalogSpecificationsGeneralizer implements SpecificationsGeneralizer {
    private final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";

    private final Map<String, String> keys = Map.ofEntries(
        Map.entry("Socket", SOCKET_KEY),
        Map.entry("Разъем (Socket)", SOCKET_KEY),
        Map.entry("Тепловыделение (TDP)", TDP_KEY),
        Map.entry("Тактовая частота", GHZ_KEY),
        Map.entry("Кол-во ядер", CORES_KEY),
        Map.entry("Форм-фактор", MOTHERBOARD_FORM_FACTOR_KEY),
        Map.entry("Кол-во потоков", THREADS_KEY),
        Map.entry("Чипсет", CHIPSET_KEY),
        Map.entry("Максимальный объем памяти", MAX_RAM_KEY),
        Map.entry("Форм-фактор слота для памяти", RAM_FORM_FACTOR_KEY),
        Map.entry("Максимальная тактовая частота", RAM_FREQUENCY_KEY)
    );

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
