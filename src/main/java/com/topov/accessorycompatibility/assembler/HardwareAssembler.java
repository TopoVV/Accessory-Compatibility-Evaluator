package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

import java.util.Map;

public interface HardwareAssembler {
    Processor assembleProcessor(Map<String, String> specifications);
    Motherboard assembleMotherboard(Map<String, String> specifications);
    Ram assembleRam(Map<String, String> generalized);
}
