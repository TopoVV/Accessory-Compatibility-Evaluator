package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface RamParser {
    Map<String, String> parseRamDom(Document document);
}
