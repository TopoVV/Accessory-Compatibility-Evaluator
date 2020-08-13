package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.hardware.Hardware;

public abstract class CompatibilityEvaluationCommandAbstract<T extends Hardware, U extends Hardware> implements CompatibilityEvaluationCommand {
    protected T component1;
    protected U component2;

    protected CompatibilityEvaluationCommandAbstract(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
    }
}
