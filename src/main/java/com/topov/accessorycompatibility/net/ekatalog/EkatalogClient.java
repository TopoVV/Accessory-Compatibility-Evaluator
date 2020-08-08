package com.topov.accessorycompatibility.net.ekatalog;

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
    public Document requestDom(String url) {
        LOG.info("Requesting processor dom from Ekatalog: " + Thread.currentThread().getName());
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.info(e);
            throw new RuntimeException("Failed to receive processor document from Ekatalog", e);
        }
    }
}
