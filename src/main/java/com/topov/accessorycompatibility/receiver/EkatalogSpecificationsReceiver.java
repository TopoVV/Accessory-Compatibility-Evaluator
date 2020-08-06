package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.net.ekatalog.EkatalogClient;
import com.topov.accessorycompatibility.parser.SpecificationsParser;
import com.topov.accessorycompatibility.service.AccessoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EkatalogSpecificationsReceiver {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecificationsReceiver.class.getName());

    private final JsoupClient client;
    private final SpecificationsParser parser;

    @Autowired
    public EkatalogSpecificationsReceiver(@Qualifier("ekatalogClient") JsoupClient client,
                                          @Qualifier("ekatalogParser") SpecificationsParser parser) {
        this.client = client;
        this.parser = parser;
    }

    @Async
    public CompletableFuture<Map<String, String>> receiveProcessorSpecifications(String processor) {
        LOG.info("Receiving processor specifications: " + Thread.currentThread().getName());
        try {
            final Document processorDom = client.getProcessorDom(processor);
            final Map<String, String> specifications = parser.parseProcessorSpecifications(processorDom);
            return CompletableFuture.completedFuture(specifications);
        } catch (RuntimeException e) {
            LOG.info(e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
