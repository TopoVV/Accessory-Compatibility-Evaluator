package com.topov.accessorycompatibility.hardware.sources;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.hardware.HardwareSource;

public class RamSource extends HardwareSource<Ram> {
    public RamSource(String source) {
        super(source);
    }

    @Override
    public Ram receive(HardwareReceiver receiver, HardwareAssembler assembler) {
        final Specifications specifications = receiver.receiveRam(this.source);
        return assembler.assembleRam(specifications);
    }
}
