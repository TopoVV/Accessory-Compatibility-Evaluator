package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityService {
    CompletableFuture<CompatibilityResult> evaluateCompatibility(CompletableFuture<CompatiblePair> pair);
}
