package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.MotherboardDomParser;
import com.topov.accessorycompatibility.parser.ProcessorDomParser;
import com.topov.accessorycompatibility.parser.RamDomParser;
import com.topov.accessorycompatibility.parser.SpecsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("ekatalogParser")
public class EkatalogSpecsParser implements SpecsParser {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecsParser.class.getName());

    private final MotherboardDomParser motherboardDomParser;
    private final ProcessorDomParser processorDomParser;
    private final RamDomParser ramDomParser;

    @Autowired
    public EkatalogSpecsParser(@Qualifier("ekatalogMotherboardParser") MotherboardDomParser motherboardDomParser,
                               @Qualifier("ekatalogProcessorParser") ProcessorDomParser processorDomParser,
                               @Qualifier("ekatalogRamParser") RamDomParser ramDomParser) {
        this.motherboardDomParser = motherboardDomParser;
        this.processorDomParser = processorDomParser;
        this.ramDomParser = ramDomParser;
    }

    @Override
    public Map<String, String> parseProcessorSpecs(Document processorDom) {
        return processorDomParser.parseProcessorDom(processorDom);
    }

    @Override
    public Map<String, String> parseMotherboardSpecs(Document motherboardDom) {
        return motherboardDomParser.parseMotherboardDom(motherboardDom);
    }

    @Override
    public Map<String, String> parseRamSpecs(Document ramUrl) {
        return ramDomParser.parseRamDom(ramUrl);

    }
}
