package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;
import com.topov.accessorycompatibility.hardware.components.Ram;
import org.springframework.stereotype.Service;

import static com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair.EvaluationCase.MOTHERBOARD_PROCESSOR;
import static com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair.EvaluationCase.MOTHERBOARD_RAM;

@Service
public class CompatibilityCaseFactory {
    public CompatibilityCase createCommand(CompatiblePair pair) {
        if (pair.getEvaluationCase().equals(MOTHERBOARD_PROCESSOR)) {
            return new PcbCpuCompatibilityCase((Pcb) pair.getComponent1(), (Cpu) pair.getComponent2());
        } else if (pair.getEvaluationCase().equals(MOTHERBOARD_RAM)) {
            return new PcbRamCompatibilityCase((Pcb) pair.getComponent1(), (Ram) pair.getComponent2());
        }
        return null;
    }
}
