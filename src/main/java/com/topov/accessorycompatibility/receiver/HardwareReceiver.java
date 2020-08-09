package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Ram;

public interface HardwareReceiver {
    Cpu receiveCpu(String processorUrl);
    Pcb receivePcb(String motherboardUrl);
    Ram receiveRam(String ramUrl);
    boolean supports(String url);
}
