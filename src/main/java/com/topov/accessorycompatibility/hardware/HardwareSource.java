package com.topov.accessorycompatibility.hardware;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class HardwareSource<T extends Hardware> {
    protected final String source;
    public abstract T receive(HardwareReceiver receiver, HardwareAssembler assembler);
    public String getSourceUrl() {
        return this.source;
    }
}
