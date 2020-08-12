package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.hardware.Hardware;
import com.topov.accessorycompatibility.hardware.HardwareSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class HadrwareReceiverInvoker {
    private static final Logger LOG = LogManager.getLogger(HadrwareReceiverInvoker.class.getName());

    private final Set<HardwareReceiver> receivers;
    private final HardwareAssembler assembler;

    @Autowired
    public HadrwareReceiverInvoker(Set<HardwareReceiver> receivers, HardwareAssembler assembler) {
        this.receivers = receivers;
        this.assembler = assembler;
    }

    @Async
    public <T extends Hardware> CompletableFuture<T> invokeReceiver(HardwareSource<T> source) {
        LOG.info(String.format("Invoking the specification receiving command: %s", source));
        try {
            final T receive = source.receive(findAppropriateReceiver(source.getSourceUrl()), assembler);
            return CompletableFuture.completedFuture(receive);
        } catch (RuntimeException e) {
            LOG.error(String.format("Failed to receive hardware (source: %s", source.getSourceUrl()));
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
