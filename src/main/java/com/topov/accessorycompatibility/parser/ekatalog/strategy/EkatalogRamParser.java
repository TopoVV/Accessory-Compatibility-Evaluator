package com.topov.accessorycompatibility.parser.ekatalog.strategy;

import com.topov.accessorycompatibility.parser.ekatalog.EkatalogParsingStrategy;
import jdk.jshell.spi.ExecutionControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class EkatalogRamParser extends EkatalogParsingStrategy {
    private static final Logger LOG = LogManager.getLogger(EkatalogRamParser.class.getName());
    @Override
    public Map<String, String> parse(Document document) {
        try {
            Map<String, String> specifications = new HashMap<>();
            final Elements parameters = removeUnnecessaryParameters(document.select(".prop"));
            final Elements values = document.select(".val");


            for(int i = 0; i < values.size(); i++) {
                final Element param = parameters.get(i);
                final Element value = values.get(i);
                final String paramName = String.format("ram-%s", param.select("span.gloss").text().trim().toLowerCase());
                final String paramValue = value.text().toLowerCase();
                specifications.put(paramName, paramValue);
            }
            return specifications;
        } catch (NullPointerException e) {
            LOG.error(String.format("Problems during parsing the Ram specifications from Ekatalog. Some elements are null: %s", e));
            throw new RuntimeException("Problems during parsing the RAM specifications from Ekatalog");
        }
    }

    @Override
    protected Elements removeUnnecessaryParameters(Elements parameters) {
        parameters.removeIf(element -> element.nextElementSibling().className().equals("small-col-plate2"));
        return parameters;
    }

    @Override
    protected Elements removeUnnecessaryValues(Elements values) {
        return values;
    }
}
