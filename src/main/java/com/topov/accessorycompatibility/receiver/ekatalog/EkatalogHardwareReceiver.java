package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Ram;
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

    public Cpu receiveCpu(String processorUlr) {
        final Specifications specifications = specificationExtractor.extractSpecifications(processorUlr, processorParser);
        return assembler.assembleCpu(specifications);
    }

    public Pcb receivePcb(String motherboardUrl) {
        final Specifications specifications = specificationExtractor.extractSpecifications(motherboardUrl, motherboardParser);
        return assembler.assemblePcb(specifications);
    }

    public Ram receiveRam(String ramUrl) {
        final  Specifications specifications = specificationExtractor.extractSpecifications(ramUrl, ramParser);
        return assembler.assembleRam(specifications);
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
