package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.accessory.Processor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccessoryModelAssemblerImplTest {
    private final AccessoryModelAssembler accessoryModelAssembler;

    @Autowired
    AccessoryModelAssemblerImplTest(AccessoryModelAssembler accessoryModelAssembler) {
        this.accessoryModelAssembler = accessoryModelAssembler;
    }

    @Test
    public void assembleProcessor() {
        Map<String, String> parsedSpecifications = new HashMap<>();
        parsedSpecifications.put("socket", "AMD AM 4");
        parsedSpecifications.put("tdp", "70");
        Processor processor = accessoryModelAssembler.assembleProcessor(parsedSpecifications);
        assertEquals("AMD AM 4", processor.getSocket());
        assertEquals(70, processor.getHeatRelease());
    }
}
