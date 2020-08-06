package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.accessory.Processor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccessoryModelAssemblerImpl implements AccessoryModelAssembler {
    @Override
    public Processor assembleProcessor(Map<String, String> specifications) {
        return Processor.builder()
                        .socket(specifications.getOrDefault("socket", "undefined"))
                        .heatRelease(Long.parseLong(specifications.getOrDefault("tdp", "-100")))
                        .build();
    }
}
