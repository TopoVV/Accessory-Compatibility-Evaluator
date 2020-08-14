package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PcbCpuCompatibilityEvaluationCommandTest {
    @Test
    public void whenSocketsAreNotEqual_ThenListOfIncompatibilitiesIsNotEmpty() {
        Cpu mockCpu = mock(Cpu.class);
        Pcb mockPcb = mock(Pcb.class);

        when(mockCpu.getSocket()).thenReturn("socket1");
        when(mockPcb.getSocket()).thenReturn("socket2");

        Incompatibility expectedIncompatibility = new Incompatibility("socket", "description");
        PcbCpuCompatibilityEvaluationCommand command = new PcbCpuCompatibilityEvaluationCommand(mockPcb, mockCpu);
        List<Incompatibility> compatibility = command.evaluate();

        assertTrue(compatibility.contains(expectedIncompatibility));
    }

    @Test
    public void whenSocketsEqual_ThenListOfIncompatibilitiesIsEmpty() {
        Cpu mockCpu = mock(Cpu.class);
        Pcb mockPcb = mock(Pcb.class);

        when(mockCpu.getSocket()).thenReturn("socket1");
        when(mockPcb.getSocket()).thenReturn("socket1");

        Incompatibility expectedIncompatibility = new Incompatibility("socket", "description");
        PcbCpuCompatibilityEvaluationCommand command = new PcbCpuCompatibilityEvaluationCommand(mockPcb, mockCpu);
        List<Incompatibility> compatibility = command.evaluate();

        assertFalse(compatibility.contains(expectedIncompatibility));
    }
}
