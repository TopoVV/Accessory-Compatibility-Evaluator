package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SpecificationsReceiver {
    CompletableFuture<Processor> receiveProcessorSpecifications(String processorName);
    CompletableFuture<Motherboard> receiveMotherboardSpecifications(String motherboardName);
}
