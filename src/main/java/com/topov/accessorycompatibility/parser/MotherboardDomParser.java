package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface MotherboardDomParser {
    Map<String, String> parseMotherboardDom(Document document);
}
