package com.topov.accessorycompatibility.parser;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EkatalogSpecificationGeneralizer implements SpecificationGeneralizer {
    private final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";

    private final Map<String, String> keys = Map.ofEntries(
        Map.entry("cpu-разъем (socket)", CPU_SOCKET_KEY),
        Map.entry("cpu-тепловыделение (tdp)", CPU_TDP_KEY),
        Map.entry("cpu-тактовая частота", CPU_FREQUENCY_KEY),
        Map.entry("cpu-кол-во потоков", CPU_THREADS_KEY),
        Map.entry("cpu-кол-во ядер", CPU_CORES_KEY),

        Map.entry("mbd-socket", PCB_SOCKET_KEY),
        Map.entry("mbd-форм-фактор", PCB_FORM_FACTOR_KEY),
        Map.entry("mbd-чипсет", PCB_CHIPSET_KEY),
        Map.entry("mbd-максимальный объем памяти", PCB_MAX_RAM_KEY),
        Map.entry("mbd-максимальная тактовая частота", PCB_MAX_RAM_FREQUENCY_KEY),
        Map.entry("mbd-форм-фактор слота для памяти", PCB_RAM_FROM_FACTOR_KEY),
        Map.entry("mbd-тип-памяти", PCB_RAM_TYPE),

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
