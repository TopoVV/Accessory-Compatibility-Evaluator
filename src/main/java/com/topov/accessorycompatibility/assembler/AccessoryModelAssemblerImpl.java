package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.receiver.EkatalogSpecificationsReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccessoryModelAssemblerImpl implements AccessoryModelAssembler {
    private static final Logger LOG = LogManager.getLogger(AccessoryModelAssemblerImpl.class.getName());

    @Override
    public Processor assembleProcessor(Map<String, String> specifications) {
        LOG.info("Assembling processor: " + Thread.currentThread().getName());
        final String socket = specifications.getOrDefault("socket", "undefined");
        final String tdp = specifications.getOrDefault("tdp", "-100");
        return Processor.builder()
                        .socket(socket)
                        .heatRelease(Long.parseLong(tdp.matches("[0-9]+") ? tdp : "-100"))
                        .build();
    }

    @Override
    public Motherboard assembleMotherboard(Map<String, String> specifications) {
        LOG.info("Assembling motherboard: " + Thread.currentThread().getName());
        final String socket = specifications.getOrDefault("socket", "undefined");
        return Motherboard.builder()
                          .socket(socket)
                          .build();
    }
}
