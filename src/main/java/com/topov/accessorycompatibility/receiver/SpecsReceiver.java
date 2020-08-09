package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.parser.strategy.HardwareParsingStrategy;

import java.util.Map;

public interface SpecsReceiver {
    Map<String, String> receiveSpecifications(String url, HardwareParsingStrategy parsingStrategy);
}
