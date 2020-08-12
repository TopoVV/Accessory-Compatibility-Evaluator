package com.topov.accessorycompatibility.extractor;

import com.topov.accessorycompatibility.client.HardwareDom;
import com.topov.accessorycompatibility.client.JsoupClient;
import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import com.topov.accessorycompatibility.parser.SpecificationGeneralizer;
import com.topov.accessorycompatibility.parser.Specifications;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EkatalogSpecificationExtractor implements SpecificationExtractor {
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecificationExtractor.class.getName());

    private final JsoupClient client;
    private final SpecificationGeneralizer generalizer;

    @Autowired
    public EkatalogSpecificationExtractor(JsoupClient client, SpecificationGeneralizer generalizer) {
        this.client = client;
        this.generalizer = generalizer;
    }

    @Override
    public Specifications extractSpecifications(String url, HardwareParsingStrategy parsingStrategy) {
        LOG.info(String.format("Extracting specifications from %s", url));
        final HardwareDom hardwareDom = client.requestDom(url);
        final Map<String, String> specs = hardwareDom.parse(parsingStrategy);
        final Map<String, String> generalized = generalizer.generalizeSpecifications(specs);
        return new Specifications(generalized);
    }
}
