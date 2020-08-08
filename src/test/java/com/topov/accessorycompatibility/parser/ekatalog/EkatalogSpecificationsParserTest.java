package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecificationsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EkatalogSpecificationsParserTest {
    private final SpecificationsParser specificationsParser;

    @Autowired
    public EkatalogSpecificationsParserTest(SpecificationsParser specificationsParser) {
        this.specificationsParser = specificationsParser;
    }

    @Test
    public void parseProcessorSpecifications() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/AMD-3600-BOX.htm").get();
        Map<String, String> specs = specificationsParser.parseProcessorSpecifications(doc);
        final String socket = specs.get("cpu-разъем (socket)");
        final String tdp = specs.get("cpu-тепловыделение (tdp)");
        assertEquals("amd am4", socket.toLowerCase());
        assertEquals("65 вт", tdp.toLowerCase());
    }

    @Test
    public void parseMotherboardSpecifications() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/ek-item.php?resolved_name_=ASUS-ROG-STRIX-B550-F-GAMING&view_=tbl").get();
        Map<String, String> specs = specificationsParser.parseMotherboardSpecifications(doc);
        final String socket = specs.get("mbd-socket");
        assertEquals("amd am4", socket.toLowerCase());
    }

    @Test
    public void receiveRamSpecificationsTest() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm").get();
        Map<String, String> specs = specificationsParser.parseRamSpecifications(doc);
        String value = specs.get("ram-объем памяти комплекта");
        assertEquals("8 гб", value.toLowerCase());
    }
}
