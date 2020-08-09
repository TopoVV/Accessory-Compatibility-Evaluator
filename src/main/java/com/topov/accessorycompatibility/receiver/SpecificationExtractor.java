package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.strategy.HardwareParsingStrategy;

import java.util.Map;

public interface SpecificationExtractor {
    Specifications receiveSpecifications(String url, HardwareParsingStrategy parsingStrategy);
}
