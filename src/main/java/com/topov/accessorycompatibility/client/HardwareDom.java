package com.topov.accessorycompatibility.client;

import com.topov.accessorycompatibility.parser.HardwareParsingStrategy;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * The wrapper for the DOM received from any source.
 */
public class HardwareDom {
    private final Document dom;
    public HardwareDom(Document dom) {
        this.dom = dom;
    }

    /**
     * Parsing is implemented as the Strategy design pattern.
     * @param strategy - a concrete parser.
     * @return - a map of specifications.
     */
    public Map<String, String> parse(HardwareParsingStrategy strategy) {
        return strategy.parse(this.dom);
    }
}
