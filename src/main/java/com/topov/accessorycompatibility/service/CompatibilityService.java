package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.Hardware;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityService {
    <T extends Hardware, U extends Hardware> CompletableFuture<CompatibilityResult>
    evaluateCompatibility(CompletableFuture<CompatibilityCase<T, U>> compatibilityCase);
}
