package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

import java.util.ArrayList;
import java.util.List;

public class PcbRamCompatibilityCase extends CompatibilityCaseAbstract<Pcb, Ram> {
    private static final String RAM_TYPES_INCOMPATIBLE = "Motherboard RAM socket (%s) is not compatible with RAM type (%s)";

    public PcbRamCompatibilityCase(Pcb pcb, Ram ram) {
        super(pcb, ram);
        this.caseName = "motherboard-ram";
    }

    @Override
    public List<Incompatibility> evaluate() {
        List<Incompatibility> incompatibilities = new ArrayList<>();
        final String pcbRamType = this.component1.getRamType();
        final String ramType = this.component2.getType();

        if (!pcbRamType.equals(ramType)) {
            final String description = String.format(RAM_TYPES_INCOMPATIBLE, pcbRamType, ramType);
            incompatibilities.add(new Incompatibility("Socket", description));
        }
        return incompatibilities;
    }
}
