package com.topov.accessorycompatibility.parser.ekatalog;

import com.topov.accessorycompatibility.client.HardwareDom;
import com.topov.accessorycompatibility.parser.strategy.EkatalogPcbParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogCpuParser;
import com.topov.accessorycompatibility.parser.strategy.EkatalogRamParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EkatalogSpecsParserTest {

    @Test
    public void parseProcessorSpecifications() throws IOException {
        final Document document = Jsoup.connect("https://ek.ua/AMD-3600-BOX.htm").get();
        final HardwareDom hardwareDom = new HardwareDom(document);
        Map<String, String> specs = hardwareDom.parse(new EkatalogCpuParser());
        final String socket = specs.get("cpu-разъем (socket)");
        final String tdp = specs.get("cpu-тепловыделение (tdp)");
        assertEquals("amd am4", socket.toLowerCase());
        assertEquals("65 вт", tdp.toLowerCase());
    }

    @Test
    public void parseMotherboardSpecifications() throws IOException {
        final Document document = Jsoup.connect("https://ek.ua/ek-item.php?resolved_name_=ASUS-ROG-STRIX-B550-F-GAMING&view_=tbl").get();
        final HardwareDom hardwareDom = new HardwareDom(document);
        Map<String, String> specs = hardwareDom.parse(new EkatalogPcbParser());
        final String socket = specs.get("mbd-socket");
        assertEquals("amd am4", socket.toLowerCase());
    }

    @Test
    public void receiveRamSpecificationsTest() throws IOException {
        final Document document = Jsoup.connect("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm").get();
        final HardwareDom hardwareDom = new HardwareDom(document);
        Map<String, String> specs = hardwareDom.parse(new EkatalogRamParser());
        String value = specs.get("ram-объем памяти комплекта");
        assertEquals("8 гб", value.toLowerCase());
    }

}
