package com.topov.accessorycompatibility.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class EkatalogJsoupClient implements JsoupClient {
    private static final String PROCESSOR_URL_PATTERN = "https://ek.ua/%s.htm";
    @Async
    @Override
    public CompletableFuture<Document> getProcessorDom(String processor) {
        try {
            final String url = String.format(PROCESSOR_URL_PATTERN, processor.replace(' ', '-'));
            final Document processorDom = Jsoup.connect(url).get();
            return CompletableFuture.completedFuture(processorDom);
        } catch (IOException e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<Document> getMotherboardDom(String motherboard) {
        return null;
    }
}
