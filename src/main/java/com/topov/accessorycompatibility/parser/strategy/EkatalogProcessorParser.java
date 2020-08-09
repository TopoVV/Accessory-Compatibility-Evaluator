package com.topov.accessorycompatibility.parser.strategy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class EkatalogProcessorParser implements HardwareParsingStrategy {
    @Override
    public Map<String, String> parse(Document document) {
        final Map<String, String> specifications = new HashMap<>();
        final Elements parameters = document.select("td.op1");
        final Elements values = document.select("td.op3");

        parameters.removeIf(element -> element.parent().children().size() < 2);
        values.removeIf(element -> element.parent().children().size() < 2);

        for(int i = 0; i < parameters.size(); i++) {
            final Element parameter = parameters.get(i).getElementsByTag("span").first();
            final Element value = values.get(i);
            final String paramName = String.format("cpu-%s", parameter.text().trim().toLowerCase());
            final String paramValue = value.text().trim().toLowerCase();
            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
