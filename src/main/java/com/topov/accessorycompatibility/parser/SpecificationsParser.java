package com.topov.accessorycompatibility.parser;

import org.jsoup.nodes.Document;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SpecificationsParser {
    Map<String, String> parseProcessorSpecifications(Document processorDom);
}
