package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;

import java.util.ArrayList;
import java.util.List;

public class PcbCpuCompatibilityEvaluationCommand extends CompatibilityEvaluationCommandAbstract<Pcb, Cpu> {
    private static final String SOCKETS_INCOMPATIBLE = "Motherboard socket (%s) is not compatible with CPU socket (%s)";

    public PcbCpuCompatibilityEvaluationCommand(Pcb pcb, Cpu cpu) {
        super(pcb, cpu);
    }

    @Override
    public List<Incompatibility> evaluate() {
        final List<Incompatibility> incompatibilities = new ArrayList<>();

        if(!this.firstComponent.isCompatibleWithCpuSocket(this.secondComponent.getSocket())) {
            final String mbSocket = this.firstComponent.getSocket();
            final String cpuSocket = this.secondComponent.getSocket();
            final String description = String.format(SOCKETS_INCOMPATIBLE, mbSocket, cpuSocket);
            incompatibilities.add(new Incompatibility("socket", description));
        }

        return incompatibilities;
    }
}
