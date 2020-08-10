package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.client.HardwareDom;
import com.topov.accessorycompatibility.client.JsoupClient;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.SpecificationGeneralizer;
import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import com.topov.accessorycompatibility.receiver.SpecificationExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogSpecificationExtractor implements SpecificationExtractor {
    private final JsoupClient client;
    private final SpecificationGeneralizer generalizer;

    @Autowired
    public EkatalogSpecificationExtractor(JsoupClient client, SpecificationGeneralizer generalizer) {
        this.client = client;
        this.generalizer = generalizer;
    }

    @Override
    public Specifications extractSpecifications(String url, HardwareParsingStrategy parsingStrategy) {
        final HardwareDom hardwareDom = client.requestDom(url);
        final Map<String, String> specs = hardwareDom.parse(parsingStrategy);
        final Map<String, String> generalized = generalizer.generalizeSpecifications(specs);
        return new Specifications(generalized);
    }
}
