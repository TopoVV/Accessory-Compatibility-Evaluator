package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.accessory.Processor;
import com.topov.accessorycompatibility.parser.AccessorySpecificationsParser;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class EkatalogAccessorySpecificationsParser implements AccessorySpecificationsParser {
    @Async
    @Override
    public CompletableFuture<Map<String, String>> parseProcessorSpecifications(Document processorDom) {
        return null;
    }
}
