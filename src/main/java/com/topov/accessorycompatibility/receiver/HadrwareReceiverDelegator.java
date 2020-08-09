package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Ram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class HadrwareReceiverDelegator {
    private static final Logger LOG = LogManager.getLogger(HadrwareReceiverDelegator.class.getName());

    private final Set<HardwareReceiver> receivers;

    @Autowired
    public HadrwareReceiverDelegator(Set<HardwareReceiver> receivers) {
        this.receivers = receivers;
    }

    @Async
    public CompletableFuture<Cpu> receiveProcessor(String processorUrl) {
        try {
            final HardwareReceiver receiver = findAppropriateReceiver(processorUrl);
            final Cpu cpu = receiver.receiveCpu(processorUrl);
            return CompletableFuture.completedFuture(cpu);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Pcb> receiveMotherboard(String motherboardUrl) {
        try {
            final HardwareReceiver receiver = findAppropriateReceiver(motherboardUrl);
            final Pcb pcb = receiver.receivePcb(motherboardUrl);
            return CompletableFuture.completedFuture(pcb);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Ram> receiveRam(String ramUrl) {
        try {
            final HardwareReceiver receiver = findAppropriateReceiver(ramUrl);
            final Ram ram = receiver.receiveRam(ramUrl);
            return CompletableFuture.completedFuture(ram);
        } catch (RuntimeException e) {
            LOG.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }

    private HardwareReceiver findAppropriateReceiver(String url) {
        return receivers.stream()
                        .filter(receiver -> receiver.supports(url))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Receivers do not support provided url."));
    }
}
