package com.topov.accessorycompatibility.parser;

import com.topov.accessorycompatibility.accessory.Processor;
import org.jsoup.nodes.Document;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface AccessorySpecificationsParser {
    CompletableFuture<Map<String, String>> parseProcessorSpecifications(Document processorDom);
}
