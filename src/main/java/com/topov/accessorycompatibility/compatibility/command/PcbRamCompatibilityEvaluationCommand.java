package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

import java.util.ArrayList;
import java.util.List;

public class PcbRamCompatibilityEvaluationCommand extends CompatibilityEvaluationCommandAbstract<Pcb, Ram> {
    private static final String RAM_TYPES_INCOMPATIBLE = "Motherboard RAM socket (%s) is not compatible with RAM type (%s)";

    public PcbRamCompatibilityEvaluationCommand(Pcb pcb, Ram ram) {
        super(pcb, ram);
    }

    @Override
    public List<Incompatibility> evaluate() {
        final List<Incompatibility> incompatibilities = new ArrayList<>();
        final String pcbRamType = this.firstComponent.getRamType();
        final String ramType = this.secondComponent.getType();

        if (!pcbRamType.equals(ramType)) {
            final String description = String.format(RAM_TYPES_INCOMPATIBLE, pcbRamType, ramType);
            incompatibilities.add(new Incompatibility("socket", description));
        }

        return incompatibilities;
    }
}
