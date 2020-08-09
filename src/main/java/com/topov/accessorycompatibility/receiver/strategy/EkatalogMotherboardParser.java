package com.topov.accessorycompatibility.receiver.strategy;

import com.topov.accessorycompatibility.receiver.HardwareParsingStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ekMotherboardParser-v2")
public class EkatalogMotherboardParser implements HardwareParsingStrategy {
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
            final String paramName =  String.format("mbd-%s", parameter.text().trim().toLowerCase());
            final String paramValue = value.text().trim().toLowerCase();

            if(paramName.contains("ddr")) {
                final String motherboardRamTypeParam = "mbd-тип-памяти";
                final String motherboardRamTypeValue = paramName.replace("mbd-", "");
                specifications.put(motherboardRamTypeParam, motherboardRamTypeValue);
            }

            specifications.put(paramName, paramValue);
        }
        return specifications;
    }
}
