package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;

import java.util.ArrayList;
import java.util.List;

public class PcbCpuCompatibilityCase extends CompatibilityCaseAbstract<Pcb, Cpu> {
    private static final String SOCKETS_INCOMPATIBLE = "Motherboard socket (%s) is not compatible with CPU socket (%s)";

    public PcbCpuCompatibilityCase(Pcb pcb, Cpu cpu) {
        super(pcb, cpu);
    }

    @Override
    public List<Incompatibility> evaluate() {
        List<Incompatibility> incompatibilities = new ArrayList<>();
        final String mbSocket = this.component1.getSocket();
        final String cpuSocket = this.component2.getSocket();
        if(!mbSocket.equals(cpuSocket)) {
            final String description = String.format(SOCKETS_INCOMPATIBLE, mbSocket, cpuSocket);
            incompatibilities.add(new Incompatibility("Socket", description));
        }
        return incompatibilities;
    }
}
