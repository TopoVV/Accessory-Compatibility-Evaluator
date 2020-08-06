package com.topov.accessorycompatibility.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

public interface JsoupClient {
    CompletableFuture<Document> getProcessorDom(String processor);
    CompletableFuture<Document> getMotherboardDom(String motherboard);
}
