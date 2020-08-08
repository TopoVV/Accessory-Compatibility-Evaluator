package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

public interface SpecsResolver {
    Processor getProcessorSpecs(String processorUrl);
    Motherboard getMotherboardSpecs(String motherboardUrl);
    Ram getRamSpecs(String ramUrl);
    boolean supports(String url);
}
