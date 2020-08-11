package com.topov.accessorycompatibility.compatibility.evaluation;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.ICompatibilityCase;
import com.topov.accessorycompatibility.hardware.Hardware;

public interface CompatibilityEvaluator {
    <T extends Hardware, U extends Hardware> CompatibilityResult startEvaluation(CompatibilityCase<T, U> compatibilityCase);
}
