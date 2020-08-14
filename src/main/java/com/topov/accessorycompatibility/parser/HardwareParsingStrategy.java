package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * The base class for all parsers, which are grouped by jsoup.Document sources.
 */
public interface HardwareParsingStrategy {
    Map<String, String> parse(Document document);
}
