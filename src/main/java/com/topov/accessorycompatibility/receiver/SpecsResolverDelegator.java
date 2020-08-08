package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class SpecsResolverDelegator {
    private static final Logger LOG = LogManager.getLogger(SpecsResolverDelegator.class.getName());

    private final Set<SpecsResolver> receivers;

    @Autowired
    public SpecsResolverDelegator(Set<SpecsResolver> receivers) {
        this.receivers = receivers;
    }

    @Async
    public CompletableFuture<Processor> receiveProcessorSpecifications(String processorUrl) {
        try {
            final SpecsResolver receiver = findAppropriateReceiver(processorUrl);
            final Processor processor = receiver.getProcessorSpecs(processorUrl);
            return CompletableFuture.completedFuture(processor);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Motherboard> receiveMotherboardSpecifications(String motherboardUrl) {
        try {
            final SpecsResolver receiver = findAppropriateReceiver(motherboardUrl);
            final Motherboard motherboard = receiver.getMotherboardSpecs(motherboardUrl);
            return CompletableFuture.completedFuture(motherboard);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Ram> receiveRamSpecifications(String ramUrl) {
        try {
            final SpecsResolver receiver = findAppropriateReceiver(ramUrl);
            final Ram ram = receiver.getRamSpecs(ramUrl);
            return CompletableFuture.completedFuture(ram);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    private SpecsResolver findAppropriateReceiver(String url) {
        return receivers.stream()
                        .filter(r -> r.supports(url))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Receivers do not support provided url."));
    }
}
