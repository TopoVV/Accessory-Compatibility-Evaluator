package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.parser.SpecsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EkatalogSpecsParserTest {
    private final SpecsParser specsParser;

    @Autowired
    public EkatalogSpecsParserTest(SpecsParser specsParser) {
        this.specsParser = specsParser;
    }

    @Test
    public void parseProcessorSpecifications() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/AMD-3600-BOX.htm").get();
        Map<String, String> specs = specsParser.parseProcessorSpecs(doc);
        final String socket = specs.get("cpu-разъем (socket)");
        final String tdp = specs.get("cpu-тепловыделение (tdp)");
        assertEquals("amd am4", socket.toLowerCase());
        assertEquals("65 вт", tdp.toLowerCase());
    }

    @Test
    public void parseMotherboardSpecifications() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/ek-item.php?resolved_name_=ASUS-ROG-STRIX-B550-F-GAMING&view_=tbl").get();
        Map<String, String> specs = specsParser.parseMotherboardSpecs(doc);
        final String socket = specs.get("mbd-socket");
        assertEquals("amd am4", socket.toLowerCase());
    }

    @Test
    public void receiveRamSpecificationsTest() throws IOException {
        final Document doc = Jsoup.connect("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm").get();
        Map<String, String> specs = specsParser.parseRamSpecs(doc);
        String value = specs.get("ram-объем памяти комплекта");
        assertEquals("8 гб", value.toLowerCase());
    }
}
