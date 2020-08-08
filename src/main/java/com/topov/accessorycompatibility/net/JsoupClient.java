package com.topov.accessorycompatibility.net;

import org.jsoup.nodes.Document;

public interface JsoupClient {
    Document requestDom(String url);
}
