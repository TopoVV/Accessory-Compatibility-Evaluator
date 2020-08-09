package com.topov.accessorycompatibility.receiver;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface HardwareParsingStrategy {
    Map<String, String> parse(Document document);
}
