package com.topov.accessorycompatibility.parser;

import java.util.Map;

public interface SpecificationGeneralizer {
    String CPU_SOCKET_KEY = "cpu-socket";
    String CPU_TDP_KEY = "cpu-tdp";
    String CPU_FREQUENCY_KEY = "cpu-freq";
    String CPU_CORES_KEY = "cpu-cores";
    String CPU_THREADS_KEY = "cpu-threads";


    String MBD_SOCKET_KEY = "mbd-socket";
    String MBD_FORM_FACTOR_KEY = "mbd-form-factor";
    String MBD_CHIPSET_KEY = "mbd-chipset";
    String MBD_MAX_RAM_KEY = "mbd-max-ram";
    String MBD_MAX_RAM_FREQUENCY_KEY = "mbd-max-ram-freq";
    String MBD_RAM_FROM_FACTOR_KEY = "mbd-ram-form-factor";
    String MBD_RAM_TYPE = "mbd-ram-type";

    String RAM_FREQUENCY_KEY = "ram-freq";
    String RAM_FORM_FACTOR_KEY = "ram-form-factor";
    String RAM_VOLTAGE_KEY = "ram-voltage";
    String RAM_TIMINGS_KEY = "ram-timings";
    String RAM_TYPE_KEY = "ram-type";

    Map<String, String> generalizeSpecifications(Map<String, String> specifications);
}
