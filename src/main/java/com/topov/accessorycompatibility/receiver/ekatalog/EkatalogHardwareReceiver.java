package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.strategy.EkatalogMotherboardParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogProcessorParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogRamParser;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.receiver.SpecificationExtractor;
import org.springframework.stereotype.Service;

@Service
public class EkatalogHardwareReceiver implements HardwareReceiver {
    private static final String SUPPORTED_URL = "https://ek.ua/";
    private final SpecificationExtractor specificationExtractor;
    private final HardwareAssembler assembler;
    private final EkatalogMotherboardParser motherboardParser;
    private final EkatalogProcessorParser processorParser;
    private final EkatalogRamParser ramParser;

    public EkatalogHardwareReceiver(SpecificationExtractor specificationExtractor, HardwareAssembler assembler) {
        this.specificationExtractor = specificationExtractor;
        this.assembler = assembler;
        this.ramParser = new EkatalogRamParser();
        this.processorParser = new EkatalogProcessorParser();
        this.motherboardParser = new EkatalogMotherboardParser();
    }

    public Processor receiveProcessor(String processorUlr) {
        final Specifications specifications = specificationExtractor.receiveSpecifications(processorUlr, processorParser);
        return assembler.assembleProcessor(specifications);
    }

    public Motherboard receiveMotherboard(String motherboardUrl) {
        final Specifications specifications = specificationExtractor.receiveSpecifications(motherboardUrl, motherboardParser);
        return assembler.assembleMotherboard(specifications);
    }

    public Ram receiveRam(String ramUrl) {
        final  Specifications specifications = specificationExtractor.receiveSpecifications(ramUrl, ramParser);
        return assembler.assembleRam(specifications);
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
