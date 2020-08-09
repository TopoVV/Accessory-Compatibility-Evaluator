package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.ProcessorParser;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import com.topov.accessorycompatibility.receiver.HardwareReceiverTemplate;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogProcessorReceiver extends HardwareReceiverTemplate<Processor> {
    private final ProcessorParser parser;

    @Autowired
    public EkatalogProcessorReceiver(@Qualifier("ekatalogClient") JsoupClient client,
                                     @Qualifier("ekatalogSpecsGeneralizer") SpecsGeneralizer generalize,
                                     @Qualifier("ekatalogProcessorParser") ProcessorParser parser,
                                     HardwareAssembler assembler) {
        super(client, generalize, assembler);
        this.parser = parser;
    }

    @Override
    protected Map<String, String> parseDom(Document document) {
        return parser.parseProcessorDom(document);
    }

    @Override
    protected Processor assembleHardware(Map<String, String> specs) {
        return assembler.assembleProcessor(specs);
    }
}
