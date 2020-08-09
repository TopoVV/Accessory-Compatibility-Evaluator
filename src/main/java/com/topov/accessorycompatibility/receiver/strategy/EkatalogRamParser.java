package com.topov.accessorycompatibility.receiver.strategy;

import com.topov.accessorycompatibility.receiver.HardwareParsingStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ekRamParser-v2")
public class EkatalogRamParser implements HardwareParsingStrategy {
    @Override
    public Map<String, String> parse(Document document) {
        Map<String, String> specifications = new HashMap<>();
        final Elements params = document.getElementsByClass("prop");
        final Elements values = document.getElementsByClass("val");

        params.removeIf(element -> element.nextElementSibling().className().equals("small-col-plate2"));

        for(int i = 0; i < params.size(); i++) {
            final Element param = params.get(i);
            final Element value = values.get(i);
            final String paramName = String.format("ram-%s", param.select("span.gloss").text().trim().toLowerCase());
            final String paramValue = value.text().toLowerCase();
            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
