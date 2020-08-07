package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface ProcessorDomParser {
    Map<String, String> parseProcessorDom(Document document);
}