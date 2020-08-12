package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.parser.Specifications;

public interface HardwareAssembler {
    Cpu assembleCpu(Specifications specifications);
    Pcb assemblePcb(Specifications specifications);
    Ram assembleRam(Specifications specifications);
}
