package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.command.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityService {
    CompletableFuture<CompatibilityResult> evaluateCompatibility(CompatibilityCase compatibilityCase);
}
