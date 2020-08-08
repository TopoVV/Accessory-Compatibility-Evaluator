package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsGeneralizer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EkatalogSpecificationsGeneralizer implements SpecificationsGeneralizer {
    private final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";

    private final Map<String, String> keys = Map.ofEntries(
        Map.entry("cpu-разъем (socket)", CPU_SOCKET_KEY),
        Map.entry("cpu-тепловыделение (tdp)", CPU_TDP_KEY),
        Map.entry("cpu-тактовая частота", CPU_FREQUENCY_KEY),
        Map.entry("cpu-кол-во потоков", CPU_THREADS_KEY),
        Map.entry("cpu-кол-во ядер", CPU_CORES_KEY),

        Map.entry("mbd-socket", MBD_SOCKET_KEY),
        Map.entry("mbd-форм-фактор", MBD_FORM_FACTOR_KEY),
        Map.entry("mbd-чипсет", MBD_CHIPSET_KEY),
        Map.entry("mbd-максимальный объем памяти", MBD_MAX_RAM_KEY),
        Map.entry("mbd-максимальная тактовая частота", MBD_MAX_RAM_FREQUENCY_KEY),
        Map.entry("mbd-форм-фактор слота для памяти", MBD_RAM_FROM_FACTOR_KEY),
        Map.entry("mbd-тип-памяти", MBD_RAM_TYPE),

        Map.entry("ram-схема таймингов памяти", RAM_TIMINGS_KEY),
        Map.entry("ram-тактовая частота", RAM_FREQUENCY_KEY),
        Map.entry("ram-форм-фактор памяти", RAM_FORM_FACTOR_KEY),
        Map.entry("ram-рабочее напряжение", RAM_VOLTAGE_KEY),
        Map.entry("ram-тип памяти", RAM_TYPE_KEY)
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
