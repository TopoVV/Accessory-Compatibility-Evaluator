package com.topov.accessorycompatibility.parser;

import java.util.Map;

public interface SpecificationsGeneralizer {
    String SOCKET_KEY = "socket";
    String TDP_KEY = "tdp";
    String GHZ_KEY = "ghz";
    String CORES_KEY = "cores";
    String THREADS_KEY = "threads";
    String MOTHERBOARD_FORM_FACTOR_KEY = "mb-form-factor";
    String MAX_RAM_KEY = "max-ram";
    String RAM_FREQUENCY_KEY = "ram-mgz";
    String RAM_FORM_FACTOR_KEY = "ram-form-factor";
    String CHIPSET_KEY = "chipset";
    String RAM_VOLTAGE_KEY = "ram-vltg";
    String RAM_TIMINGS_KEY = "ram-timings";
    String RAM_TYPE_KEY = "ram-type";

    Map<String, String> generalizeSpecifications(Map<String, String> specifications);
}
