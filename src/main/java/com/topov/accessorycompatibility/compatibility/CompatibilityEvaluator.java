package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Hardware;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CompatibilityEvaluator {
    Compatibility startEvaluation(CompatibilityEvaluationCommand command);
}
