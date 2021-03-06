package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.extractor.SpecificationExtractor;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.ekatalog.EkatalogParsingStrategy;
import com.topov.accessorycompatibility.receiver.SpecificationReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogSpecificationReceiver implements SpecificationReceiver {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecificationReceiver.class.getName());
    private static final String SUPPORTED_URL = "https://ek.ua/";
    private final SpecificationExtractor specificationExtractor;
    private final Map<String, EkatalogParsingStrategy> parsers;

    @Autowired
    public EkatalogSpecificationReceiver(@Qualifier("ekatalogSpecificationExtractor") SpecificationExtractor specificationExtractor,
                                         Map<String, EkatalogParsingStrategy> parsers) {
        this.specificationExtractor = specificationExtractor;
        this.parsers = parsers;
    }

    public Specifications receiveCpu(String cpuUrl) {
        LOG.info(String.format("Receiving specifications of the CPU from Ekatalog (%s)", cpuUrl));
        return specificationExtractor.extractSpecifications(cpuUrl, parsers.get("ekatalogCpuParser"));
    }

    public Specifications receivePcb(String pcbUrl) {
        LOG.info(String.format("Receiving specifications of the PCB from Ekatalog (%s)", pcbUrl));
        return specificationExtractor.extractSpecifications(pcbUrl, parsers.get("ekatalogPcbParser"));
    }

    public Specifications receiveRam(String ramUrl) {
        LOG.info(String.format("Receiving specifications of the RAM from Ekatalog (%s)", ramUrl));
        return specificationExtractor.extractSpecifications(ramUrl, parsers.get("ekatalogRamParser"));
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
