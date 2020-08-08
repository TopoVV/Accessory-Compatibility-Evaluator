package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SpecificationsReceiver {
    Processor receiveProcessorSpecifications(String processorUrl);
    Motherboard receiveMotherboardSpecifications(String motherboardUrl);
    Ram receiveRamSpecifications(String ramUrl);
    boolean supports(String url);
}
