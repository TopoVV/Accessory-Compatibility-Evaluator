package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.MotherboardParser;
import com.topov.accessorycompatibility.parser.ProcessorParser;
import com.topov.accessorycompatibility.parser.RamParser;
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

    private final MotherboardParser motherboardParser;
    private final ProcessorParser processorParser;
    private final RamParser ramParser;

    @Autowired
    public EkatalogSpecsParser(@Qualifier("ekatalogMotherboardParser") MotherboardParser motherboardParser,
                               @Qualifier("ekatalogProcessorParser") ProcessorParser processorParser,
                               @Qualifier("ekatalogRamParser") RamParser ramParser) {
        this.motherboardParser = motherboardParser;
        this.processorParser = processorParser;
        this.ramParser = ramParser;
    }

    @Override
    public Map<String, String> parseProcessorSpecs(Document processorDom) {
        return processorParser.parseProcessorDom(processorDom);
    }

    @Override
    public Map<String, String> parseMotherboardSpecs(Document motherboardDom) {
        return motherboardParser.parseMotherboardDom(motherboardDom);
    }

    @Override
    public Map<String, String> parseRamSpecs(Document ramUrl) {
        return ramParser.parseRamDom(ramUrl);

    }
}
