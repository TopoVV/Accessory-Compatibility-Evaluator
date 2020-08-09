package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Proc;

import java.util.ArrayList;
import java.util.List;

public class MotherboardProcessorCompatibility extends CompatibilityEvaluationAbstract<Motherboard, Processor> {
    private static final String HARDWARE_PAIR_NAME = "MOTHERBOARD-PROCESSOR";
    private static final String SOCKETS_INCOMPATIBLE = "Motherboard socket (%s) is not compatible with CPU socket (%s)";

    protected MotherboardProcessorCompatibility(Motherboard component1, Processor component2) {
        super(component1, component2);
    }

    @Override
    public List<Incompatibility> evaluate() {
        List<Incompatibility> incompatibilities = new ArrayList<>();
        final String mbSocket = this.component1.getSocket();
        final String cpuSocket = this.component2.getSocket();
        if(!mbSocket.equals(cpuSocket)) {
            final String description = String.format(SOCKETS_INCOMPATIBLE, mbSocket, cpuSocket);
            incompatibilities.add(new Incompatibility(HARDWARE_PAIR_NAME, "Socket", description));
        }
        return incompatibilities;
    }
}
