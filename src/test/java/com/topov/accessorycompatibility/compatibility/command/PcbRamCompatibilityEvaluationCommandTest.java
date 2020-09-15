package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PcbRamCompatibilityEvaluationCommandTest {
    @Test
    public void whenSocketsAreNotEqual_ThenListOfIncompatibilitiesIsNotEmpty() {
        Pcb mockPcb = mock(Pcb.class);
        Ram mockRam = mock(Ram.class);

        when(mockRam.getType()).thenReturn("ddr3");
        when(mockPcb.isCompatibleWithRamType("ddr4")).thenReturn(false);


        Incompatibility expectedIncompatibility = new Incompatibility("socket", "description");
        PcbRamCompatibilityEvaluationCommand command = new PcbRamCompatibilityEvaluationCommand(mockPcb, mockRam);
        List<Incompatibility> compatibility = command.evaluate();

        assertTrue(compatibility.contains(expectedIncompatibility));
    }

    @Test
    public void whenSocketsEqual_ThenListOfIncompatibilitiesIsEmpty() {
        Pcb mockPcb = mock(Pcb.class);
        Ram mockRam = mock(Ram.class);

        when(mockRam.getType()).thenReturn("ddr4");
        when(mockPcb.isCompatibleWithRamType("ddr4")).thenReturn(true);

        Incompatibility expectedIncompatibility = new Incompatibility("socket", "description");
        PcbRamCompatibilityEvaluationCommand command = new PcbRamCompatibilityEvaluationCommand(mockPcb, mockRam);
        List<Incompatibility> compatibility = command.evaluate();

        assertFalse(compatibility.contains(expectedIncompatibility));
    }
}
