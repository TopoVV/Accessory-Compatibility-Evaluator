package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HardwareAssemblerImplTest {
    private final HardwareAssembler hardwareAssembler;

    @Autowired
    HardwareAssemblerImplTest(HardwareAssembler hardwareAssembler) {
        this.hardwareAssembler = hardwareAssembler;
    }

    @Test
    public void assembleProcessor() {
        Map<String, String> parsedSpecifications = new HashMap<>();
        parsedSpecifications.put(SpecsGeneralizer.CPU_SOCKET_KEY, "amd am 4");
        parsedSpecifications.put(SpecsGeneralizer.CPU_TDP_KEY, "70");
        Processor processor = hardwareAssembler.assembleProcessor(parsedSpecifications);
        assertEquals("amd am 4", processor.getSocket());
        assertEquals(70, processor.getHeatRelease());
    }

    @Test
    public void assembleProcessorWithEmptyProperty() {
        Map<String, String> parsedSpecifications = new HashMap<>();
        parsedSpecifications.put(SpecsGeneralizer.CPU_SOCKET_KEY, "amd am 4");
        parsedSpecifications.put(SpecsGeneralizer.CPU_TDP_KEY, "");
        Processor processor = hardwareAssembler.assembleProcessor(parsedSpecifications);
        assertEquals("amd am 4", processor.getSocket());
        assertEquals(-100, processor.getHeatRelease());
    }
}
