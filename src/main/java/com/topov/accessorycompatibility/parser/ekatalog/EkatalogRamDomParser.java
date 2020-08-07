package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.RamDomParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ekatalogRamParser")
public class EkatalogRamDomParser implements RamDomParser {
    private static final Logger LOG = LogManager.getLogger(EkatalogRamDomParser.class.getName());
    @Override
    public Map<String, String> parseRamDom(Document document) {
        Map<String, String> specifications = new HashMap<>();
        final Elements params = document.getElementsByClass("prop");
        final Elements values = document.getElementsByClass("val");

        params.removeIf(element -> element.nextElementSibling().className().equals("small-col-plate2"));

        for(int i = 0; i < params.size(); i++) {
            final Element param = params.get(i);
            final Element value = values.get(i);
            final String paramName = param.select("span.gloss").text();
            final String paramValue = value.text();

            LOG.info(paramName + "   ====   " + paramValue);
            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
