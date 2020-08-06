package com.topov.accessorycompatibility.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccessorySpecificationsParserFactory {
    private final Map<String, AccessorySpecificationsParser> parsers;

    @Autowired
    public AccessorySpecificationsParserFactory(Map<String, AccessorySpecificationsParser> parsers) {
        this.parsers = parsers;
    }

    public AccessorySpecificationsParser getSpecificationsParser(String parserName) {
        return parsers.get(parserName);
    }
}
