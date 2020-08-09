package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

public interface HardwareReceiver {
    Processor receiveProcessor(String processorUrl);
    Motherboard receiveMotherboard(String motherboardUrl);
    Ram receiveRam(String ramUrl);
    boolean supports(String url);
}
