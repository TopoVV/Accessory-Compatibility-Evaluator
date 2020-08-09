package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;

import java.util.concurrent.CompletableFuture;

public interface CompatibilityService {
    CompletableFuture<Compatibility> evaluateCompatibility(CompletableFuture<CompatiblePair> pair);
}
