package com.topov.accessorycompatibility.hardware.sources;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import com.topov.accessorycompatibility.hardware.HardwareSource;

public class CpuSource extends HardwareSource<Cpu> {
    public CpuSource(String source) {
        super(source);
    }

    @Override
    public Cpu receive(HardwareReceiver receiver, HardwareAssembler assembler) {
        final Specifications specifications = receiver.receiveCpu(this.source);
        return assembler.assembleCpu(specifications);
    }
}
