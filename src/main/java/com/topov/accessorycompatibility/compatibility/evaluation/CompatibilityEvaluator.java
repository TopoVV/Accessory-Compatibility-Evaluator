package com.topov.accessorycompatibility.compatibility.evaluation;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;

public interface CompatibilityEvaluator {
    CompatibilityResult startEvaluation(CompatibilityCase command);
}
