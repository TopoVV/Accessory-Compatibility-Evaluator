package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.MotherboardParser;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import com.topov.accessorycompatibility.receiver.HardwareReceiverTemplate;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogMotherboardReceiver extends HardwareReceiverTemplate<Motherboard> {
    private final MotherboardParser parser;

    @Autowired
    public EkatalogMotherboardReceiver(@Qualifier("ekatalogClient") JsoupClient client,
                                       @Qualifier("ekatalogSpecsGeneralizer") SpecsGeneralizer generalize,
                                       @Qualifier("ekatalogMotherboardParser") MotherboardParser parser,
                                       HardwareAssembler assembler) {
        super(client, generalize, assembler);
        this.parser = parser;
    }

    @Override
    protected Map<String, String> parseDom(Document document) {
        return parser.parseMotherboardDom(document);
    }

    @Override
    protected Motherboard assembleHardware(Map<String, String> specs) {
        return assembler.assembleMotherboard(specs);
    }
}
