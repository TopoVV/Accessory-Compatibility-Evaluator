package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.strategy.EkatalogParsingStrategy;
import com.topov.accessorycompatibility.parser.strategy.EkatalogPcbParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogCpuParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogRamParser;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.receiver.SpecificationExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogHardwareReceiver implements HardwareReceiver {
    private static final String SUPPORTED_URL = "https://ek.ua/";
    private final HardwareAssembler assembler;
    private final SpecificationExtractor specificationExtractor;
    private final Map<String, EkatalogParsingStrategy> parsers;

    @Autowired
    public EkatalogHardwareReceiver(Map<String, EkatalogParsingStrategy> parsers,
                                    SpecificationExtractor specificationExtractor,
                                    HardwareAssembler assembler) {
        this.specificationExtractor = specificationExtractor;
        this.assembler = assembler;
        this.parsers = parsers;
    }

    public Cpu receiveCpu(String processorUlr) {
        final Specifications specifications = specificationExtractor.extractSpecifications(processorUlr, parsers.get("ekatalogCpuParser"));
        return assembler.assembleCpu(specifications);
    }

    public Pcb receivePcb(String motherboardUrl) {
        final Specifications specifications = specificationExtractor.extractSpecifications(motherboardUrl, parsers.get("ekatalogPcbParser"));
        return assembler.assemblePcb(specifications);
    }

    public Ram receiveRam(String ramUrl) {
        final  Specifications specifications = specificationExtractor.extractSpecifications(ramUrl, parsers.get("ekatalogRamParser"));
        return assembler.assembleRam(specifications);
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
