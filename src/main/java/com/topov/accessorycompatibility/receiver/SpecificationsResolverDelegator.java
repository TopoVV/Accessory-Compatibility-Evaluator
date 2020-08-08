package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class SpecificationsResolverDelegator {
    private final Set<SpecificationsReceiver> receivers;

    @Autowired
    public SpecificationsResolverDelegator(Set<SpecificationsReceiver> receivers) {
        this.receivers = receivers;
    }

    @Async
    public CompletableFuture<Processor> receiveProcessorSpecifications(String processorUrl) {
        try {
            final SpecificationsReceiver receiver = findAppropriateReceiver(processorUrl);
            final Processor processor = receiver.receiveProcessorSpecifications(processorUrl);
            return CompletableFuture.completedFuture(processor);
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(e);
        }
    };
    @Async
    public CompletableFuture<Motherboard> receiveMotherboardSpecifications(String motherboardUrl) {
        try {
            final SpecificationsReceiver receiver = findAppropriateReceiver(motherboardUrl);
            final Motherboard motherboard = receiver.receiveMotherboardSpecifications(motherboardUrl);
            return CompletableFuture.completedFuture(motherboard);
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(e);
        }
    };

    @Async
    public CompletableFuture<Ram> receiveRamSpecifications(String ramUrl) {
        try {
            final SpecificationsReceiver receiver = findAppropriateReceiver(ramUrl);
            final Ram ram = receiver.receiveRamSpecifications(ramUrl);
            return CompletableFuture.completedFuture(ram);
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(e);
        }
    };

    private SpecificationsReceiver findAppropriateReceiver(String url) {
        return receivers.stream()
                        .filter(r -> r.supports(url))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Receivers do not support provided url."));
    }
}
