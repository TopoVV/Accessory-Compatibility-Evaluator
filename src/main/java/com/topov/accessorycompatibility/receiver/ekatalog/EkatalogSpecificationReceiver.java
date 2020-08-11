package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.strategy.EkatalogParsingStrategy;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.extractor.SpecificationExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogSpecificationReceiver implements HardwareReceiver {
    private static final String SUPPORTED_URL = "https://ek.ua/";
    private final SpecificationExtractor specificationExtractor;
    private final Map<String, EkatalogParsingStrategy> parsers;

    @Autowired
    public EkatalogSpecificationReceiver(Map<String, EkatalogParsingStrategy> parsers,
                                         SpecificationExtractor specificationExtractor) {
        this.specificationExtractor = specificationExtractor;
        this.parsers = parsers;
    }

    public Specifications receiveCpu(String processorUlr) {
        return specificationExtractor.extractSpecifications(processorUlr, parsers.get("ekatalogCpuParser"));
    }

    public Specifications receivePcb(String motherboardUrl) {
        return specificationExtractor.extractSpecifications(motherboardUrl, parsers.get("ekatalogPcbParser"));
    }

    public Specifications receiveRam(String ramUrl) {
        return specificationExtractor.extractSpecifications(ramUrl, parsers.get("ekatalogRamParser"));
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
