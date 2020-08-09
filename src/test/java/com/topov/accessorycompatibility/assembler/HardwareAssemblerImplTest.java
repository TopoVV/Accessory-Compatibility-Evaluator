package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.parser.Specifications;
import com.topov.accessorycompatibility.parser.SpecificationGeneralizer;
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
        parsedSpecifications.put(SpecificationGeneralizer.CPU_SOCKET_KEY, "amd am 4");
        parsedSpecifications.put(SpecificationGeneralizer.CPU_TDP_KEY, "70");
        final Specifications specifications = new Specifications(parsedSpecifications);
        Processor processor = hardwareAssembler.assembleProcessor(specifications);
        assertEquals("amd am 4", processor.getSocket());
        assertEquals(70, processor.getHeatRelease());
    }

    @Test
    public void assembleProcessorWithEmptyProperty() {
        Map<String, String> parsedSpecifications = new HashMap<>();
        parsedSpecifications.put(SpecificationGeneralizer.CPU_SOCKET_KEY, "amd am 4");
        parsedSpecifications.put(SpecificationGeneralizer.CPU_TDP_KEY, "");
        final Specifications specifications = new Specifications(parsedSpecifications);
        Processor processor = hardwareAssembler.assembleProcessor(specifications);
        assertEquals("amd am 4", processor.getSocket());
        assertEquals(-100, processor.getHeatRelease());
    }
}
