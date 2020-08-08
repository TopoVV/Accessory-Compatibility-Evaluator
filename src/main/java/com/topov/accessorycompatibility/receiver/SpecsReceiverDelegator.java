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
public class SpecsReceiverDelegator {
    private static final Logger LOG = LogManager.getLogger(SpecsReceiverDelegator.class.getName());

    private final Set<SpecsReceiver> receivers;

    @Autowired
    public SpecsReceiverDelegator(Set<SpecsReceiver> receivers) {
        this.receivers = receivers;
    }

    @Async
    public CompletableFuture<Processor> receiveProcessor(String processorUrl) {
        try {
            final SpecsReceiver receiver = findAppropriateReceiver(processorUrl);
            final Processor processor = receiver.receiveProcessor(processorUrl);
            return CompletableFuture.completedFuture(processor);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Motherboard> receiveMotherboard(String motherboardUrl) {
        try {
            final SpecsReceiver receiver = findAppropriateReceiver(motherboardUrl);
            final Motherboard motherboard = receiver.receiveMotherboard(motherboardUrl);
            return CompletableFuture.completedFuture(motherboard);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Ram> receiveRam(String ramUrl) {
        try {
            final SpecsReceiver receiver = findAppropriateReceiver(ramUrl);
            final Ram ram = receiver.receiveRam(ramUrl);
            return CompletableFuture.completedFuture(ram);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    private SpecsReceiver findAppropriateReceiver(String url) {
        return receivers.stream()
                        .filter(receiver -> receiver.supports(url))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Receivers do not support provided url."));
    }
}
