package com.topov.accessorycompatibility.compatibility.evaluation;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.dto.response.Compatibility;

public interface CompatibilityEvaluator {
    Compatibility startEvaluation(CompatibilityCase command);
}
