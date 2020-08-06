package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.AccessorySpecificationsParser;
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
import java.util.concurrent.CompletableFuture;

@Service
public class EkatalogAccessorySpecificationsParser implements AccessorySpecificationsParser {
    private static final String CYRILLIC = "[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя]";
    private static final Logger LOG = LogManager.getLogger(EkatalogAccessorySpecificationsParser.class.getName());

    private final SpecificationKeyGeneralizer specificationKeyGeneralizer;

    public EkatalogAccessorySpecificationsParser(SpecificationKeyGeneralizer specificationKeyGeneralizer) {
        this.specificationKeyGeneralizer = specificationKeyGeneralizer;
    }

    @Async
    @Override
    public CompletableFuture<Map<String, String>> parseProcessorSpecifications(Document processorDom) {
        LOG.info(Thread.currentThread().getName());
        final Map<String, String> specifications = new HashMap<>();
        final Elements parametersHTML = processorDom.select("td.op1");
        final Elements valuesHTML = processorDom.select("td.op3");

        valuesHTML.removeIf(element -> element.parent().children().size() < 2 && element.text().equals(""));
        parametersHTML.removeIf(element -> element.parent().children().size() < 2);

        for(int i = 0; i < parametersHTML.size() - 1; i++) {
            final Element paramHTML = parametersHTML.get(i).getElementsByTag("span").first();
            final Element valueHTML = valuesHTML.get(i);
            final String paramName = specificationKeyGeneralizer.getAppropriateKey(paramHTML.text().trim());
            final String paramValue = valueHTML.text().equals("") ? "true" : valueHTML.text().replaceAll(CYRILLIC, "").trim();
            LOG.info(paramName + " : " + paramValue);
            specifications.put(paramName, paramValue);

        }
        return CompletableFuture.completedFuture(specifications);
    }

}