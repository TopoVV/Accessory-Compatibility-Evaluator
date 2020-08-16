package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

import java.util.ArrayList;
import java.util.List;

public class PcbRamCompatibilityEvaluationCommand extends CompatibilityEvaluationCommandAbstract<Pcb, Ram> {
    private static final String RAM_TYPES_INCOMPATIBLE = "Motherboard RAM socket (%s) is not compatible with RAM type (%s)";
    private static final String RAM_FORM_FACTORS_INCOMPATIBLE = "Motherboard RAM form factor (%s) is not compatible with RAM form factor (%s)";

    public PcbRamCompatibilityEvaluationCommand(Pcb pcb, Ram ram) {
        super(pcb, ram);
    }

    @Override
    public List<Incompatibility> evaluate() {
        final List<Incompatibility> incompatibilities = new ArrayList<>();
        final String pcbRamType = this.firstComponent.getRamType();
        final String ramType = this.secondComponent.getType();
        final String pcbRamFormFactor = this.firstComponent.getRamFormFactor();
        final String ramFormFactor = this.secondComponent.getFormFactor();

        if (!pcbRamType.equals(ramType)) {
            final String description = String.format(RAM_TYPES_INCOMPATIBLE, pcbRamType, ramType);
            incompatibilities.add(new Incompatibility("socket", description));
        }

        if (!pcbRamFormFactor.equals(ramFormFactor)) {
            final String description = String.format(RAM_FORM_FACTORS_INCOMPATIBLE, pcbRamFormFactor, ramFormFactor);
            incompatibilities.add(new Incompatibility("form factor", description));
        }

        return incompatibilities;
    }
}
