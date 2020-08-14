package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;

public abstract class EkatalogParsingStrategy implements HardwareParsingStrategy {
    public abstract Map<String, String> parse(Document document);
    protected abstract Elements removeUnnecessaryParameters(Elements parameters);
    protected abstract Elements removeUnnecessaryValues(Elements values);
}
