package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.model.Hardware;

public abstract class CompatibilityEvaluationAbstract<T extends Hardware, U extends Hardware>
    implements CompatibilityEvaluationCommand {

    protected final T component1;
    protected final U component2;

    protected CompatibilityEvaluationAbstract(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
    }
}
