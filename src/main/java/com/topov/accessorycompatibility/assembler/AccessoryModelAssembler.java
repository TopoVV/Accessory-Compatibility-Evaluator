package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Processor;

import java.util.Map;

public interface AccessoryModelAssembler {
    Processor assembleProcessor(Map<String, String> specifications);
}
