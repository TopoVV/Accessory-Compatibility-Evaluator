package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.parser.Specifications;

public interface SpecificationReceiver {
    Specifications receiveCpu(String processorUrl);
    Specifications receivePcb(String motherboardUrl);
    Specifications receiveRam(String ramUrl);
    boolean supports(String url);
}
