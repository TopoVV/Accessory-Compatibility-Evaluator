package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

import java.util.ArrayList;
import java.util.List;

public class PcbRamCompatibilityEvaluationCommand implements CompatibilityEvaluationCommand {
    private static final String RAM_TYPES_INCOMPATIBLE = "Motherboard RAM socket (%s) is not compatible with RAM type (%s)";
    private static final String RAM_FORM_FACTORS_INCOMPATIBLE = "Motherboard RAM form factor (%s) is not compatible with RAM form factor (%s)";

    private final Pcb pcb;
    private final Ram ram;

    public PcbRamCompatibilityEvaluationCommand(Pcb pcb, Ram ram) {
        this.pcb = pcb;
        this.ram = ram;
    }

    @Override
    public List<Incompatibility> evaluate() {
        final List<Incompatibility> incompatibilities = new ArrayList<>();
        final String ramType = this.ram.getType();


        if (!this.pcb.isCompatibleWithRamType(ramType)) {
            final String pcbRamType = this.pcb.getRamType();
            final String description = String.format(RAM_TYPES_INCOMPATIBLE, pcbRamType, ramType);
            incompatibilities.add(new Incompatibility("socket", description));
        }

        if (!this.pcb.isCompatibleWithRamFormFactor(this.ram.getFormFactor())) {
            final String pcbRamFormFactor = this.pcb.getRamFormFactor();
            final String ramFormFactor = this.ram.getFormFactor();
            final String description = String.format(RAM_FORM_FACTORS_INCOMPATIBLE, pcbRamFormFactor, ramFormFactor);
            incompatibilities.add(new Incompatibility("form factor", description));
        }

        return incompatibilities;
    }
}
