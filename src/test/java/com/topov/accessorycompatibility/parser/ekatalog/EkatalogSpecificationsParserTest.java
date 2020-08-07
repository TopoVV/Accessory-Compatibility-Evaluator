package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EkatalogSpecificationsParserTest {
    private final SpecificationsParser specificationsParser;

    @Autowired
    EkatalogSpecificationsParserTest(SpecificationsParser specificationsParser) {
        this.specificationsParser = specificationsParser;
    }

    @Test
    public void parseProcessorSpecifications() {
        Document doc = Jsoup.parse("<td class='op01' width='48%'>" +
            "<table width='100%' cellspacing='0' cellpadding='3' border='0'>" +
            "<tbody>" +

            "<tr valign='top'>" +
            "<td class='op1' width='49%'>" +
            "<span class='gloss' jtype='click' jsource='https://ek.ua/mtools/mui_gloss.php?idGloss_=7478' jid='p7478' jsub='Y'>" +
            "Разъем <span class='nobr ib'>" +
            "(Socket)</span>" +
            "</span>" +
            "</td>" +
            "<td class='op3' width='51%'>" +
            "<a href='/ek-list.php?katalog_=187&amp;idgm_=1485970'>" +
            "AMD AM4</a>" +
            "</td>" +
            "</tr>" +

            "<tr valign='top'>" +
            "<td class='op1' width='49%'>" +
            "<span class='gloss' jtype='click' jsource='https://ek.ua/mtools/mui_gloss.php?idGloss_=7492' jid='p7492' jsub='Y'>" +
            "Тепловыделение <span class='nobr ib'>" +
            "(TDP)</span>" +
            "</span>" +
            "</td>" +
            "<td class='op3' width='51%'>" +
            "65&nbsp;Вт</td>" +
            "</tr>" +

            "</tbody>" +
            "</table>" +
            "</td>" +
            "");
        System.out.println(doc);
        Map<String, String> specificationsMap = specificationsParser.parseProcessorSpecifications(doc);
        final String socket = specificationsMap.get("Разъем (Socket)");
        final String tdp = specificationsMap.get("Тепловыделение (TDP)");
        assertEquals("amd am4", socket.toLowerCase());
        assertEquals("65 вт", tdp.toLowerCase());
    }
}
