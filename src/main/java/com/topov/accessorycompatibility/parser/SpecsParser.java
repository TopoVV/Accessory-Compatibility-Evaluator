package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface SpecsParser {
    Map<String, String> parseProcessorSpecs(Document processorDom);
    Map<String, String> parseMotherboardSpecs(Document motherboardDom);
    Map<String, String> parseRamSpecs(Document ramDom);
}
