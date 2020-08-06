package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsParser;
import com.topov.accessorycompatibility.parser.SpecificationKeyGeneralizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service("ekatalogParser")
public class EkatalogSpecificationsParser implements SpecificationsParser {
    private static final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";
    private static final Logger LOG = LogManager.getLogger(EkatalogSpecificationsParser.class.getName());

    private final SpecificationKeyGeneralizer specificationKeyGeneralizer;

    public EkatalogSpecificationsParser(SpecificationKeyGeneralizer specificationKeyGeneralizer) {
        this.specificationKeyGeneralizer = specificationKeyGeneralizer;
    }

    @Override
    public Map<String, String> parseProcessorSpecifications(Document processorDom) {
        LOG.info("Parsing processor specifications: " + Thread.currentThread().getName());
        return parseDom(processorDom);
    }

    @Override
    public Map<String, String> parseMotherboardSpecifications(Document motherboardDom) {
        LOG.info("Parsing motherboard specifications: " + Thread.currentThread().getName());
        return parseDom(motherboardDom);
    }

    private Map<String, String> parseDom(Document document) {
        final Map<String, String> specifications = new HashMap<>();
        final Elements parameters = document.select("td.op1");
        final Elements values = document.select("td.op3");

        parameters.removeIf(element -> element.parent().children().size() < 2);
        values.removeIf(element -> element.parent().children().size() < 2);

        for(int i = 0; i < parameters.size() - 1; i++) {
            final Element parameter = parameters.get(i).getElementsByTag("span").first();
            final Element value = values.get(i);
            final String parsedParameterName = parameter.text().trim();
            final String parsedParameterValue = value.text().trim();
            final String paramName = specificationKeyGeneralizer.getAppropriateKey(parsedParameterName);
            final String paramValue = parsedParameterValue.equals("") ? "true" : parsedParameterValue.replaceAll(CYRILLIC, "").trim();
            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
