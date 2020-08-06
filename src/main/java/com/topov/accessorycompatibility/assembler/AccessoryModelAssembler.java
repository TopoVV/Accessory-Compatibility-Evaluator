package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.accessory.Processor;

import java.util.Map;

public interface AccessoryModelAssembler {
    Processor assembleProcessor(Map<String, String> specifications);
}
