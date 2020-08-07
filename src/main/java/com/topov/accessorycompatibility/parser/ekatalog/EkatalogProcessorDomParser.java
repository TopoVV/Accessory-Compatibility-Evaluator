package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.ProcessorDomParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ekatalogProcessorParser")
public class EkatalogProcessorDomParser implements ProcessorDomParser {
    @Override
    public Map<String, String> parseProcessorDom(Document document) {
        final Map<String, String> specifications = new HashMap<>();
        final Elements parameters = document.select("td.op1");
        final Elements values = document.select("td.op3");

        parameters.removeIf(element -> element.parent().children().size() < 2);
        values.removeIf(element -> element.parent().children().size() < 2);

        for(int i = 0; i < parameters.size(); i++) {
            final Element parameter = parameters.get(i).getElementsByTag("span").first();
            final Element value = values.get(i);
            final String paramName = parameter.text().trim();
            final String paramValue = value.text().trim();;
            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
