package com.topov.accessorycompatibility.net;

import org.jsoup.nodes.Document;

public interface JsoupClient {
    Document getProcessorDom(String processorUrl);
    Document getMotherboardDom(String motherboardUrl);
    Document getRamDom(String ramUrl);
}
