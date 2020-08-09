package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCaseFactory;
import com.topov.accessorycompatibility.compatibility.cases.PcbCpuCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbRamCompatibilityCase;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePairImpl;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompatibilityCaseFactoryTest {
    private final CompatibilityCaseFactory factory = new CompatibilityCaseFactory();

    @Test
    public void whenCreateCommandForPcbCpuPair_ThenPcbCpuCompatibilityCase() {
        final Pcb pcb = mock(Pcb.class);
        final Cpu cpu = mock(Cpu.class);

        final CompatiblePair compatiblePair = CompatiblePair.pcbCpuPair(pcb, cpu);
        final CompatibilityCase command = factory.createCommand(compatiblePair);
        assertTrue(command instanceof PcbCpuCompatibilityCase);
    }

    @Test
    public void whenCreateCommandForPcbRamPair_ThenPcbRamCompatibilityCase() {
        final Pcb pcb = mock(Pcb.class);
        final Ram ram = mock(Ram.class);

        final CompatiblePair compatiblePair = CompatiblePair.pcbRamPair(pcb, ram);
        final CompatibilityCase command = factory.createCommand(compatiblePair);
        assertTrue(command instanceof PcbRamCompatibilityCase);
    }
}
