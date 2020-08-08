package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface SpecificationsParser {
    Map<String, String> parseProcessorSpecifications(Document processorDom);
    Map<String, String> parseMotherboardSpecifications(Document motherboardDom);
    Map<String, String> parseRamSpecifications(Document ramDom);
}
