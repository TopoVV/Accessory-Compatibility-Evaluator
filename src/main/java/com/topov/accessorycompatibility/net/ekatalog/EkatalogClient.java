package com.topov.accessorycompatibility.net.ekatalog;

import com.topov.accessorycompatibility.assembler.AccessoryModelAssemblerImpl;
import com.topov.accessorycompatibility.net.JsoupClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


/**
 * Receives specifications of the specified accessory as jsoup.Document from Ekatalog.
 * */
@Service("ekatalogClient")
public class EkatalogClient implements JsoupClient {
    private static final Logger LOG = LogManager.getLogger(EkatalogClient.class.getName());
    private static final String MOTHERBOARD_URL_PATTERN = "https://ek.ua/ek-item.php?resolved_name_=%s&view_=tbl";
    private static final String PROCESSOR_URL_PATTERN = "https://ek.ua/%s.htm";

    @Override
    public Document getProcessorDom(String processor) {
        LOG.info("Requesting processor dom from ekatalog: " + Thread.currentThread().getName());
        try {
            final String url = String.format(PROCESSOR_URL_PATTERN, processor.replace(' ', '-'));
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.info(e);
            throw new RuntimeException("Failed to receive document from Ekatalog", e);
        }
    }

    @Override
    public Document getMotherboardDom(String motherboard) {
        LOG.info("Requesting motherboard dom from ekatalog: " + Thread.currentThread().getName());
        try {
            final String url = String.format(MOTHERBOARD_URL_PATTERN, motherboard.replace(' ', '-'));
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.info(e);
            throw new RuntimeException("Failed to receive document from Ekatalog", e);
        }
    }
}
