package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.net.HardwareDom;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import com.topov.accessorycompatibility.parser.strategy.HardwareParsingStrategy;
import com.topov.accessorycompatibility.receiver.SpecsReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogSpecsReceiver implements SpecsReceiver {
    private final JsoupClient client;
    private final SpecsGeneralizer generalizer;

    @Autowired
    public EkatalogSpecsReceiver(JsoupClient client, SpecsGeneralizer generalizer) {
        this.client = client;
        this.generalizer = generalizer;
    }

    @Override
    public Map<String, String> receiveSpecifications(String url, HardwareParsingStrategy parsingStrategy) {
        final HardwareDom hardwareDom = client.requestDom(url);
        final Map<String, String> specs = hardwareDom.parse(parsingStrategy);
        return generalizer.generalizeSpecifications(specs);
    }
}
