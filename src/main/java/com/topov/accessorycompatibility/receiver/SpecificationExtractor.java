package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;

public interface SpecificationExtractor {
    Specifications extractSpecifications(String url, HardwareParsingStrategy parsingStrategy);
}
