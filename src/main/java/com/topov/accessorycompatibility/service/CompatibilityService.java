package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCaseAbstract;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.Hardware;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityService {
    CompletableFuture<CompatibilityResult> evaluateCompatibility(CompletableFuture<CompatibilityCase> compatibilityCase);
}
