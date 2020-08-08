package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.MotherboardProcessorCompatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityEvaluatorImpl implements CompatibilityEvaluator {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluatorImpl.class.getName());

    @Async
    public CompletableFuture<MotherboardProcessorCompatibility> checkCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard) {
        LOG.info("Evaluating components compatibility: " + Thread.currentThread().getName());
        return processor.thenCombine(motherboard, (prc, mbr) -> {
            final var compatibility = new MotherboardProcessorCompatibility("success", "Compatibility");
            final boolean socket = prc.getSocket().equals(mbr.getSocket());
            compatibility.setIsSocketCompatible(socket);
            return compatibility;
        }).exceptionally(throwable -> {
            LOG.error("ERROR PROCESSOR/MOTHERBOARD " + throwable);
            return new MotherboardProcessorCompatibility("failed", throwable.getCause().getMessage());
        });
    }
}
