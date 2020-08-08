package com.topov.accessorycompatibility.delegator;

import com.topov.accessorycompatibility.parser.SpecificationsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecificationsParserDelegator {
    private final Set<SpecificationsParser> parsers;

    @Autowired
    public SpecificationsParserDelegator(Set<SpecificationsParser> parsers) {
        this.parsers = parsers;
    }


}
