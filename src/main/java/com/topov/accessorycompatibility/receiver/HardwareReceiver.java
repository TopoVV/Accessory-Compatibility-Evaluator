package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.hardware.Hardware;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.parser.Specifications;

public interface HardwareReceiver {
    Specifications receiveCpu(String processorUrl);
    Specifications receivePcb(String motherboardUrl);
    Specifications receiveRam(String ramUrl);
    boolean supports(String url);
}
