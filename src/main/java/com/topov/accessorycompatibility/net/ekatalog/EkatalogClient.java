package com.topov.accessorycompatibility.net.ekatalog;

import com.topov.accessorycompatibility.net.HardwareDom;
import com.topov.accessorycompatibility.net.JsoupClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Receives specifications of the specified accessory as jsoup.Document from Ekatalog.
 * */
@Service("ekatalogClient")
public class EkatalogClient implements JsoupClient {
    private static final Logger LOG = LogManager.getLogger(EkatalogClient.class.getName());

    @Override
    public HardwareDom requestDom(String url) {
        LOG.info("Requesting processor dom from Ekatalog: " + Thread.currentThread().getName());
        try {
            final Document document = Jsoup.connect(url).get();
            return new HardwareDom(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to receive processor document from Ekatalog", e);
        }
    }
}
