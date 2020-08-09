package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.RamParser;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import com.topov.accessorycompatibility.receiver.HardwareReceiverTemplate;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogRamReceiver extends HardwareReceiverTemplate<Ram> {
    private final RamParser parser;

    @Autowired
    public EkatalogRamReceiver(@Qualifier("ekatalogClient") JsoupClient client,
                               @Qualifier("ekatalogSpecsGeneralizer") SpecsGeneralizer generalize,
                               @Qualifier("ekatalogRamParser") RamParser parser,
                               HardwareAssembler assembler) {
        super(client, generalize, assembler);
        this.parser = parser;
    }


    @Override
    protected Map<String, String> parseDom(Document document) {
        return parser.parseRamDom(document);
    }

    @Override
    protected Ram assembleHardware(Map<String, String> specs) {
        return assembler.assembleRam(specs);
    }
}
