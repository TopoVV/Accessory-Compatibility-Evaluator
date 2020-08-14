package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import org.jsoup.nodes.Document;

import java.util.Map;

public interface EkatalogParsingStrategy extends HardwareParsingStrategy {
    Map<String, String> parse(Document document);
}
