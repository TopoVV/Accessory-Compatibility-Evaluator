package com.topov.accessorycompatibility.parser;

import java.util.Map;

public interface SpecificationsGeneralizer {
    String SOCKET_KEY = "socket";
    String TDP_KEY = "tdp";
    String GHZ_KEY = "ghz";
    Map<String, String> generalizeSpecifications(Map<String, String> specifications);
}
