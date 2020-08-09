package com.topov.accessorycompatibility.net;

import com.topov.accessorycompatibility.parser.strategy.HardwareParsingStrategy;
import org.jsoup.nodes.Document;

import java.util.Map;

public class HardwareDom {
    private final Document dom;
    public HardwareDom(Document dom) {
        this.dom = dom;
    }
    public Map<String, String> parse(HardwareParsingStrategy strategy) {
        return strategy.parse(this.dom);
    }
}
