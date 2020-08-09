package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.parser.strategy.EkatalogMotherboardParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogProcessorParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogRamParser;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.receiver.SpecsReceiver;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogHardwareReceiver implements HardwareReceiver {
    private static final String SUPPORTED_URL = "https://ek.ua/";
    private final SpecsReceiver specsReceiver;
    private final HardwareAssembler assembler;
    private final EkatalogMotherboardParser motherboardParser;
    private final EkatalogProcessorParser processorParser;
    private final EkatalogRamParser ramParser;

    public EkatalogHardwareReceiver(SpecsReceiver specsReceiver, HardwareAssembler assembler) {
        this.specsReceiver = specsReceiver;
        this.assembler = assembler;
        this.ramParser = new EkatalogRamParser();
        this.processorParser = new EkatalogProcessorParser();
        this.motherboardParser = new EkatalogMotherboardParser();
    }

    public Processor receiveProcessor(String processorUlr) {
        final Map<String, String> specs = specsReceiver.receiveSpecifications(processorUlr, processorParser);
        return assembler.assembleProcessor(specs);
    }

    public Motherboard receiveMotherboard(String motherboardUrl) {
        final Map<String, String> specs = specsReceiver.receiveSpecifications(motherboardUrl, motherboardParser);
        return assembler.assembleMotherboard(specs);
    }

    public Ram receiveRam(String ramUrl) {
        final Map<String, String> specs = specsReceiver.receiveSpecifications(ramUrl, ramParser);
        return assembler.assembleRam(specs);
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
