package com.topov.accessorycompatibility.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface JsoupClient {
    Document getProcessorDom(String processor);
    Document getMotherboardDom(String motherboard);
}
