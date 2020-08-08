package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityEvaluator {
    CompletableFuture<Compatibility> checkMotherboardProcessorCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard);
    CompletableFuture<Compatibility> checkMotherboardRamCompatibility(CompletableFuture<Ram> ram, CompletableFuture<Motherboard> motherboard);

}
