package com.topov.accessorycompatibility.extractor;

import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import com.topov.accessorycompatibility.parser.Specifications;

public interface SpecificationExtractor {
    Specifications extractSpecifications(String url, HardwareParsingStrategy parsingStrategy);
}
