package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.MotherboardProcessorCompatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface CompatibilityEvaluator {
    CompletableFuture<MotherboardProcessorCompatibility> checkCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard);
}
