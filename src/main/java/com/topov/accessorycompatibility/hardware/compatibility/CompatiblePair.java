package com.topov.accessorycompatibility.hardware.compatibility;

import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePairImpl.EvaluationCase;
import com.topov.accessorycompatibility.hardware.Hardware;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

public interface CompatiblePair {
    static CompatiblePair pcbCpuPair(Pcb pcb, Cpu cpu) {
        return new CompatiblePairImpl(pcb, cpu, EvaluationCase.MOTHERBOARD_PROCESSOR);
    }

    static CompatiblePair pcbRamPair(Pcb pcb, Ram ram) {
        return new CompatiblePairImpl(pcb, ram, EvaluationCase.MOTHERBOARD_RAM);
    }

    Hardware getComponent1();
    Hardware getComponent2();
    EvaluationCase getEvaluationCase();
}
