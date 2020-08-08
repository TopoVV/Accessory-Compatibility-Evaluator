package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.SpecsModelAssembler;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import com.topov.accessorycompatibility.parser.SpecsParser;
import com.topov.accessorycompatibility.receiver.SpecsResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EkatalogSpecsResolver implements SpecsResolver {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecsResolver.class.getName());
    private static final String SUPPORTED_URL = "https://ek.ua/";

    private final JsoupClient client;
    private final SpecsParser parser;
    private final SpecsModelAssembler accessoryAssembler;
    private final SpecsGeneralizer specsGeneralizer;

    @Autowired
    public EkatalogSpecsResolver(SpecsModelAssembler accessoryAssembler,
                                 @Qualifier("ekatalogClient") JsoupClient client,
                                 @Qualifier("ekatalogParser") SpecsParser parser,
                                 @Qualifier("ekatalogSpecsGeneralizer") SpecsGeneralizer specsGeneralizer) {
        this.client = client;
        this.parser = parser;
        this.accessoryAssembler = accessoryAssembler;
        this.specsGeneralizer = specsGeneralizer;
    }

    @Override
    public Processor getProcessorSpecs(String processorUrl) {
        LOG.info("Receiving processor specifications: " + Thread.currentThread().getName());
        return Optional.of(client.requestDom(processorUrl))
                       .map(parser::parseProcessorSpecs)
                       .map(specsGeneralizer::generalizeSpecifications)
                       .map(accessoryAssembler::assembleProcessor)
                       .orElseThrow(() -> new RuntimeException("Specs model creation exception!"));
    }

    @Override
    public Motherboard getMotherboardSpecs(String motherboardUrl) {
        LOG.info("Receiving motherboard specifications: " + Thread.currentThread().getName());
        return Optional.of(client.requestDom(motherboardUrl))
                .map(parser::parseMotherboardSpecs)
                .map(specsGeneralizer::generalizeSpecifications)
                .map(accessoryAssembler::assembleMotherboard)
                .orElseThrow(() -> new RuntimeException("Specs model creation exception!"));
    }

    @Override
    public Ram getRamSpecs(String ramUrl) {
        LOG.info("Receiving ram specifications: " + Thread.currentThread().getName());
        return Optional.of(client.requestDom(ramUrl))
                       .map(parser::parseRamSpecs)
                       .map(specsGeneralizer::generalizeSpecifications)
                       .map(accessoryAssembler::assembleRam)
                       .orElseThrow(() -> new RuntimeException("Specs model creation exception!"));
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
