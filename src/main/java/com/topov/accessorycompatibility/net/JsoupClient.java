package com.topov.accessorycompatibility.net;

import org.jsoup.nodes.Document;

public interface JsoupClient {
    Document getProcessorDom(String processor);
    Document getMotherboardDom(String motherboard);
}
