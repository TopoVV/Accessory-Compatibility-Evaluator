package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.parser.Specifications;

import java.util.Map;

public interface HardwareAssembler {
    Processor assembleProcessor(Specifications specifications);
    Motherboard assembleMotherboard(Specifications specifications);
    Ram assembleRam(Specifications specifications);
}
