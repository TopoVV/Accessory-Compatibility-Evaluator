package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.AccessoryModelAssembler;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.SpecificationsGeneralizer;
import com.topov.accessorycompatibility.parser.SpecificationsParser;
import com.topov.accessorycompatibility.receiver.SpecificationsReceiver;
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
public class EkatalogSpecificationsReceiver implements SpecificationsReceiver {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecificationsReceiver.class.getName());

    private final JsoupClient client;
    private final SpecificationsParser parser;
    private final AccessoryModelAssembler accessoryAssembler;
    private final SpecificationsGeneralizer specificationsGeneralizer;

    @Autowired
    public EkatalogSpecificationsReceiver(AccessoryModelAssembler accessoryAssembler,
                                          @Qualifier("ekatalogClient") JsoupClient client,
                                          @Qualifier("ekatalogParser") SpecificationsParser parser,
                                          @Qualifier("ekatalogSpecificationsGeneralizer") SpecificationsGeneralizer specificationsGeneralizer) {
        this.client = client;
        this.parser = parser;
        this.accessoryAssembler = accessoryAssembler;
        this.specificationsGeneralizer = specificationsGeneralizer;
    }

    @Async
    @Override
    public CompletableFuture<Processor> receiveProcessorSpecifications(String processorName) {
        LOG.info("Receiving processor specifications: " + Thread.currentThread().getName());
        try {
            final Document processorDom = client.getProcessorDom(processorName);
            final Map<String, String> specifications = parser.parseProcessorSpecifications(processorDom);
            final Map<String, String> generalized = specificationsGeneralizer.generalizeSpecifications(specifications);
            final Processor processor = accessoryAssembler.assembleProcessor(generalized);
            return CompletableFuture.completedFuture(processor);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<Motherboard> receiveMotherboardSpecifications(String motherboardName) {
        LOG.info("Receiving motherboard specifications: " + Thread.currentThread().getName());
        try {
            final Document processorDom = client.getMotherboardDom(motherboardName);
            final Map<String, String> specifications = parser.parseMotherboardSpecifications(processorDom);
            final Map<String, String> generalized = specificationsGeneralizer.generalizeSpecifications(specifications);
            final Motherboard motherboard = accessoryAssembler.assembleMotherboard(generalized);
            return CompletableFuture.completedFuture(motherboard);
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<Ram> receiveRamSpecifications(String ramName) {
        return null;
    }
}
