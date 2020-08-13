package com.topov.accessorycompatibility.client.ekatalog;

import com.topov.accessorycompatibility.client.HardwareDom;
import com.topov.accessorycompatibility.client.JsoupClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Receives specifications of the hardware as jsoup.Document from Ekatalog.
 * */
@Service("ekatalogClient")
public class EkatalogClient implements JsoupClient {
    private static final Logger LOG = LogManager.getLogger(EkatalogClient.class.getName());

    @Override
    public HardwareDom requestDom(String url) {
        LOG.info(String.format("Fetching the DOM from %s (Ekatalog)", url));
        try {
            return new HardwareDom(Jsoup.connect(url).get());
        } catch (IOException e) {
            LOG.error(String.format("Exception while fetching the DOM  from %s (Ekatalog)", url), e);
            throw new RuntimeException(String.format("Failed to receive DOM from %s (Ekatalog)", url), e);
        }
    }
}
