package com.topov.accessorycompatibility.hardware.sources;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.HardwareSource;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.receiver.SpecificationReceiver;

public class PcbSource extends HardwareSource<Pcb> {
    public PcbSource(String source) {
        super(source);
    }

    @Override
    public Pcb receive(SpecificationReceiver receiver, HardwareAssembler assembler) {
        final Specifications specifications = receiver.receivePcb(this.source);
        return assembler.assemblePcb(specifications);
    }
}
