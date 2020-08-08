package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityEvaluator {
    CompletableFuture<Compatibility> evaluateProcessorMotherboardCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard);
    CompletableFuture<Compatibility> evaluateMotherboardRamCompatibility(CompletableFuture<Ram> ram, CompletableFuture<Motherboard> motherboard);

}
