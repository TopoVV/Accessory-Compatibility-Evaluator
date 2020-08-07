package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface RamDomParser {
    Map<String, String> parseRamDom(Document document);
}
