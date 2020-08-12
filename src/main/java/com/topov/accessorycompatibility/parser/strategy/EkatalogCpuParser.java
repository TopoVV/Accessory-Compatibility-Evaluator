package com.topov.accessorycompatibility.parser.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EkatalogCpuParser implements EkatalogParsingStrategy {
    private static final Logger LOG = LogManager.getLogger(EkatalogParsingStrategy.class.getName());
    @Override
    public Map<String, String> parse(Document document) {
        try {
            final Map<String, String> specifications = new HashMap<>();
            final Elements parameters = removeUnnecessaryParameters(document.select("td.op1"));
            final Elements values = removeUnnecessaryValues(document.select("td.op3"));

            for(int i = 0; i < values.size(); i++) {
                final Element parameter = parameters.get(i).getElementsByTag("span").first();
                final Element value = values.get(i);
                final String paramName = String.format("cpu-%s", parameter.text().trim().toLowerCase());
                final String paramValue = value.text().trim().toLowerCase();
                specifications.put(paramName, paramValue);
            }
            return specifications;
        } catch (NullPointerException e) {
            LOG.error(String.format("Problems during parsing the CPU specifications from Ekatalog. Some elements are null: %s", e));
            throw new RuntimeException("Problems during parsing the CPU specifications from Ekatalog");
        }
    }

    private Elements removeUnnecessaryParameters(Elements parameters) {
        parameters.removeIf(element -> element.parent().children().size() < 2);
        return parameters;
    }

    private Elements removeUnnecessaryValues(Elements values) {
        values.removeIf(element -> element.parent().children().size() < 2);
        return values;
    }
}
