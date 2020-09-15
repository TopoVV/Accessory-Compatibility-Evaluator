package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;

import java.util.ArrayList;
import java.util.List;

public class PcbCpuCompatibilityEvaluationCommand implements CompatibilityEvaluationCommand {
    private static final String SOCKETS_INCOMPATIBLE = "Motherboard socket (%s) is not compatible with CPU socket (%s)";
    private final Pcb pcb;
    private final Cpu cpu;

    public PcbCpuCompatibilityEvaluationCommand(Pcb pcb, Cpu cpu) {
        this.pcb = pcb;
        this.cpu = cpu;
    }

    @Override
    public List<Incompatibility> evaluate() {
        final List<Incompatibility> incompatibilities = new ArrayList<>();

        if(!this.pcb.isCompatibleWithCpuSocket(this.cpu.getSocket())) {
            final String mbSocket = this.pcb.getSocket();
            final String cpuSocket = this.cpu.getSocket();
            final String description = String.format(SOCKETS_INCOMPATIBLE, mbSocket, cpuSocket);
            incompatibilities.add(new Incompatibility("socket", description));
        }

        return incompatibilities;
    }
}
